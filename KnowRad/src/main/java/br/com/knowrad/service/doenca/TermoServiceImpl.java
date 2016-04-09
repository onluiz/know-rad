package br.com.knowrad.service.doenca;

import br.com.knowrad.dao.doenca.TermoDAO;
import br.com.knowrad.dto.doenca.TermoDTO;
import br.com.knowrad.entity.doenca.Termo;
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

    public List<Termo> findListByIdDoenca(Long id) {
        return dao.findListByIdDoenca(id);
    }

    public List<TermoDTO> findListDTOByIdDoenca(Long id) {
        List<Termo> list = findListByIdDoenca(id);
        List<TermoDTO> listDTO = new ArrayList<TermoDTO>();

        for(Termo termo : list)
            listDTO.add(entityToDTO(termo));

        return listDTO;
    }

    TermoDTO entityToDTO(Termo termo) {
        TermoDTO dto = new TermoDTO();
        dto.setId(termo.getId());
        dto.setNomeTermo(termo.getNomeTermo());
        return dto;
    }

}
