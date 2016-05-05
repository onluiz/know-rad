package br.com.knowrad.dao.patologia;

import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.patologia.Termo;

import java.util.List;

public interface TermoDAO {
    void persist(Termo termo);
    void merge(Termo termo);
    void remove(final Long id);
    Termo findById(final Long id);
    List<Termo> findListByIdPatologia(Long id);
    List<TermoDTO> findAllDTO();
}
