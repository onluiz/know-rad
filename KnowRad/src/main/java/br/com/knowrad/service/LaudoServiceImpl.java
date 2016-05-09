package br.com.knowrad.service;

import br.com.knowrad.dao.LaudoDAO;
import br.com.knowrad.entity.Laudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class LaudoServiceImpl implements LaudoService {

    @Autowired
    private LaudoDAO dao;

    public void persist(Laudo l) {
        dao.persist(l);
    }

    public void merge(Laudo l) {
        dao.merge(l);
    }

    public List<Laudo> findAll() {
        return dao.findAll();
    }

    public Laudo findByAccessionNo(String accessionNo) {
        return dao.findByAccessionNo(accessionNo);
    }
}
