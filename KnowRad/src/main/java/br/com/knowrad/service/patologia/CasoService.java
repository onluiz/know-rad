
package br.com.knowrad.service.patologia;

import br.com.knowrad.dto.CasoDTO;
import br.com.knowrad.entity.patologia.Caso;

import java.util.List;

public interface CasoService {
    void persist(Caso c);
    void merge(Caso c);
    void remove(final Long id);
    void removeFull(Long idCaso);
    Caso findById(final Long id);
    CasoDTO findDTOById(Long id);
    List<Caso> findAll();
    List<CasoDTO> findAllDTO();
}
