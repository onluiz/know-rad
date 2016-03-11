package br.com.knowrad.dao.patologia;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.CasoDTO;
import br.com.knowrad.dto.patologia.CasoFilterDTO;
import br.com.knowrad.entity.patologia.Caso;

import java.util.List;

public interface CasoDAO {
    void persist(Caso c);
    void merge(Caso c);
    void remove(final Long id);
    Caso findById(final Long id);
    List<Caso> findAll();
    DatatableResponse<CasoDTO> findListDatatableByFilter(DatatableRequest datatableRequest, CasoFilterDTO filter);
}
