package br.com.knowrad.service;

import br.com.knowrad.dao.FakeNamesDAO;
import br.com.knowrad.entity.FakeNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class FakeNamesServiceImpl implements FakeNamesService {

    @Autowired
    private FakeNamesDAO dao;

    public void persist(FakeNames fakeNames) {
        dao.persist(fakeNames);
    }

    public void merge(FakeNames fakeNames) {
        dao.merge(fakeNames);
    }

    public List<FakeNames> findAll() {
        return dao.findAll();
    }
}
