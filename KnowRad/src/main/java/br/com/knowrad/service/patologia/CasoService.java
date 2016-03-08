
package br.com.knowrad.service.patologia;

import br.com.knowrad.dto.CasoDTO;
import br.com.knowrad.entity.patologia.Caso;

import java.util.List;

public interface CasoService {
    void persist(Caso c);
    void merge(Caso c);
    void remove(final Long id);
    Caso findById(final Long id);
    List<Caso> findAll();
    List<CasoDTO> findAllDTO();
}
