package br.com.knowrad.dao.doenca;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.dto.doenca.DoencaFilter;
import br.com.knowrad.entity.doenca.Doenca;

import java.util.List;

public interface DoencaDAO {
    void persist(Doenca c);
    void merge(Doenca c);
    void remove(final Long id);
    Doenca findById(final Long id);
    List<Doenca> findAll();
    DatatableResponse<DoencaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, DoencaFilter filter);
}
