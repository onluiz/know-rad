package br.com.knowrad.service;

import br.com.knowrad.dao.PacienteDAO;
import br.com.knowrad.dto.PacienteDTO;
import br.com.knowrad.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteDAO dao;

    public void persist(Paciente p) {
        dao.persist(p);
    }

    public List<Paciente> findAll() {
        return dao.findAll();
    }

    public List<PacienteDTO> findAllDTO() {
        List<PacienteDTO> listDTO = new ArrayList<PacienteDTO>();
        for(Paciente paciente : findAll()) {
            PacienteDTO dto = new PacienteDTO();
            dto.setId(paciente.getId());
            dto.setNome(paciente.getNome());
            listDTO.add(dto);
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

    PacienteDTO entityToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
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
