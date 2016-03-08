package br.com.knowrad.dao.patologia;

import br.com.knowrad.entity.patologia.Patologia;

public interface PatologiaDAO {
    void persist(Patologia c);
    void merge(Patologia c);
    void remove(final Long id);
    Patologia findById(final Long id);
}
