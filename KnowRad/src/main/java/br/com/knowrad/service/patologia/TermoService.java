package br.com.knowrad.service.patologia;

import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.patologia.Termo;

import java.util.List;

public interface TermoService {
    void persist(Termo termo);
    void merge(Termo termo);
    void remove(final Long id);
    Termo findById(final Long id);
    List<Termo> findListByIdPatologia(Long id);
    List<TermoDTO> findListDTOByIdPatologia(Long id);
    List<TermoDTO> findAllDTO();
}
