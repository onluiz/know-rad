package br.com.knowrad.dao.patologia;

import br.com.knowrad.entity.patologia.Caso;

import java.util.List;

public interface CasoDAO {
    void persist(Caso c);
    void merge(Caso c);
    void remove(final Long id);
    Caso findById(final Long id);
    List<Caso> findAll();
}
