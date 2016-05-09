package br.com.knowrad.dao;

import br.com.knowrad.entity.Laudo;

import java.util.List;

public interface LaudoDAO {
    void persist(Laudo l);
    void merge(Laudo l);
    List<Laudo> findAll();
    Laudo findByAccessionNo(String accessionNo);
}
