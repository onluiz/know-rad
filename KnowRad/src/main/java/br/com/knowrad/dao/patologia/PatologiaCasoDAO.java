package br.com.knowrad.dao.patologia;

import br.com.knowrad.entity.patologia.PatologiaCaso;

import java.util.List;

public interface PatologiaCasoDAO {
    void persist(PatologiaCaso c);
    void remove(final Long id);
    PatologiaCaso findById(final Long id);
    PatologiaCaso findByIds(Long idCaso, Long idPatologia);
    List<PatologiaCaso> findAllByIdCaso(Long id);
}
