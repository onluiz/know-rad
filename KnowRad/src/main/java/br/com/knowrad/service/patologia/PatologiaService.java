package br.com.knowrad.service.patologia;

import br.com.knowrad.entity.patologia.Patologia;

public interface PatologiaService {
    void persist(Patologia c);
    void merge(Patologia c);
    void remove(final Long id);
    Patologia findById(final Long id);
}
