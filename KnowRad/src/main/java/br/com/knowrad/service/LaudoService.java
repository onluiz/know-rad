package br.com.knowrad.service;

import br.com.knowrad.entity.Laudo;

import java.util.List;

public interface LaudoService {
    void persist(Laudo l);
    void merge(Laudo l);
    List<Laudo> findAll();
    Laudo findByAccessionNo(String accessionNo);
}
