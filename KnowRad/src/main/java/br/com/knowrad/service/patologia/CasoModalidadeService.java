package br.com.knowrad.service.patologia;

import br.com.knowrad.entity.patologia.CasoModalidade;

import java.util.List;

public interface CasoModalidadeService {
    void persist(CasoModalidade c);
    void remove(final Long id);
    CasoModalidade findByIds(Long idCaso, Long idModalidade);
    List<CasoModalidade> findAllByIdCaso(Long idCaso);
}
