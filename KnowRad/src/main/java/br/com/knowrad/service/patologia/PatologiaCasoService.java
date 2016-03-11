package br.com.knowrad.service.patologia;

import br.com.knowrad.entity.patologia.PatologiaCaso;

import java.util.List;

public interface PatologiaCasoService {
    void persist(PatologiaCaso c);
    void remove(final Long id);
    PatologiaCaso findById(final Long id);
    PatologiaCaso findByIds(Long idCaso, Long idPatologia);
    List<PatologiaCaso> findAllByIdCaso(Long id);
}
