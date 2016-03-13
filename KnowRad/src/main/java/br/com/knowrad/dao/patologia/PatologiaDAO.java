package br.com.knowrad.dao.patologia;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilterDTO;
import br.com.knowrad.entity.patologia.Patologia;

import java.util.List;

public interface PatologiaDAO {
    void persist(Patologia c);
    void merge(Patologia c);
    void remove(final Long id);
    Patologia findById(final Long id);
    DatatableResponse<PatologiaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, PatologiaFilterDTO filter);
    List<PatologiaDTO> search(String searchText, Integer limit);
}
