package br.com.knowrad.service;

import br.com.knowrad.dao.PacienteDAO;
import br.com.knowrad.dto.PacienteDTO;
import br.com.knowrad.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteDAO dao;

    public static List<PacienteDTO> listPacienteDTO;

    @PostConstruct
    public void init() {
        refreshList();
    }

    public void refreshList() {
        listPacienteDTO = findAllDTO();
    }

    public void persist(Paciente p) {
        dao.persist(p);
    }

    public void merge(Paciente p) {
        dao.merge(p);
    }

    public List<Paciente> findAll() {
        return dao.findAll();
    }

    public List<PacienteDTO> findAllDTO() {
        List<PacienteDTO> listDTO = new ArrayList<PacienteDTO>();
        for(Paciente paciente : findAll()) {
            listDTO.add(entityToDTO(paciente));
        }
        return listDTO;
    }

    public Paciente findById(Long id) {
        return dao.findById(id);
    }

    public PacienteDTO findDTOById(Long id) {
        return entityToDTO(findById(id));
    }

    public Paciente findByPatId(String patId) {
        return dao.findByPatId(patId);
    }

    public PacienteDTO findDTOByPatId(String patId) {
        return entityToDTO(findByPatId(patId));
    }

    public PacienteDTO findByPatIdInList(String patId) {
        PacienteDTO pacienteDTO = null;
        int count = 0;
        while(pacienteDTO == null && count <= this.listPacienteDTO.size() - 1) {
            PacienteDTO dto = this.listPacienteDTO.get(count);
            if(dto.getPatId().equals(patId))
                pacienteDTO = dto;
            count++;
        }
        return pacienteDTO;
    }

    public List<PacienteDTO> getStaticList() {
        return this.listPacienteDTO;
    }

    PacienteDTO entityToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setPatId(paciente.getPatId());
        dto.setSelected(Boolean.FALSE);
        dto.setCanonicalName(dto.getNome());
        dto.setCytoscape_alias_list(new String[] {dto.getNome()});
        dto.setNodeType("CheeseType");
        dto.setNodeTypeFormatted("CheeseType");
        dto.setName(dto.getNome());
        dto.setShared_name(dto.getNome());
        dto.setSUID("");
        return dto;
    }
}
