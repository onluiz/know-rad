package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PatologiaDAO;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.entity.patologia.Patologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PatologiaServiceImpl implements PatologiaService {

    @Autowired
    private PatologiaDAO dao;

    public void persist(Patologia c) {
        dao.persist(c);
    }

    public void merge(Patologia c) {
        dao.merge(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public Patologia findById(Long id) {
        return dao.findById(id);
    }

    public List<PatologiaDTO> search(String searchText, Integer limit) {
        return dao.search(searchText, limit);
    }
}
