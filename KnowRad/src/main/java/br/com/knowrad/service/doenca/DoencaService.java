package br.com.knowrad.service.doenca;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.dto.doenca.DoencaFilter;
import br.com.knowrad.entity.doenca.Doenca;

import java.util.List;

public interface DoencaService {
    void persist(Doenca c);
    void merge(Doenca c);
    void remove(final Long id);
    void removeFull(final Long id);
    Doenca findById(final Long id);
    List<Doenca> findAll();
    DatatableResponse<DoencaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, DoencaFilter filter);
    List<DoencaDTO> findAllDTO();
    List<String> getTermosByIdDoenca(Long id);
}
