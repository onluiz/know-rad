package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PalavraChaveDAO;
import br.com.knowrad.entity.patologia.PalavraChave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PalavraChaveServiceImpl implements PalavraChaveService {

    @Autowired
    private PalavraChaveDAO dao;

    public void persist(PalavraChave c) {
        dao.persist(c);
    }

    public void merge(PalavraChave c) {
        dao.merge(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public PalavraChave findById(Long id) {
        return dao.findById(id);
    }
}
