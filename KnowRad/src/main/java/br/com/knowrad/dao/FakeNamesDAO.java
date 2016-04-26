package br.com.knowrad.dao;

import br.com.knowrad.entity.FakeNames;

import java.util.List;

public interface FakeNamesDAO {
    void persist(FakeNames fakeNames);
    void merge(FakeNames fakeNames);
    List<FakeNames> findAll();
}
