package br.com.knowrad.service.patologia;

import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.entity.patologia.Patologia;

import java.util.List;

public interface PatologiaService {
    void persist(Patologia c);
    void merge(Patologia c);
    void remove(final Long id);
    Patologia findById(final Long id);
    List<PatologiaDTO> search(String searchText, Integer limit);
}
