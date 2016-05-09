package br.com.knowrad.service;

import br.com.knowrad.entity.FakeNames;

import java.util.List;

public interface FakeNamesService {
    void persist(FakeNames fakeNames);
    void merge(FakeNames fakeNames);
    List<FakeNames> findAll();
    FakeNames findOneNotUsed();
    String namePattern();
}
