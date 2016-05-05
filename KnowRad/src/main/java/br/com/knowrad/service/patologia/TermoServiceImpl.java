package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.TermoDAO;
import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.patologia.Termo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class TermoServiceImpl implements TermoService {

    @Autowired
    private TermoDAO dao;

    public void persist(Termo termo) {
        dao.persist(termo);
    }

    public void merge(Termo termo) {
        dao.merge(termo);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public Termo findById(Long id) {
        return dao.findById(id);
    }

    public List<Termo> findListByIdPatologia(Long id) {
        return dao.findListByIdPatologia(id);
    }

    public List<TermoDTO> findListDTOByIdPatologia(Long id) {
        List<Termo> list = findListByIdPatologia(id);
        List<TermoDTO> listDTO = new ArrayList<TermoDTO>();

        for(Termo termo : list)
            listDTO.add(entityToDTO(termo));

        return listDTO;
    }

    public List<TermoDTO> findAllDTO() {
        return dao.findAllDTO();
    }

    TermoDTO entityToDTO(Termo termo) {
        TermoDTO dto = new TermoDTO();
        dto.setId(termo.getId());
        dto.setNomeTermo(termo.getNomeTermo());
        return dto;
    }

}
