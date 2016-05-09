package br.com.knowrad.service.patologia;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilter;
import br.com.knowrad.entity.patologia.Patologia;

import java.util.List;

public interface PatologiaService {
    void persist(Patologia c);
    void merge(Patologia c);
    void remove(final Long id);
    void removeFull(final Long id);
    Patologia findById(final Long id);
    List<Patologia> findAll();
    DatatableResponse<PatologiaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, PatologiaFilter filter);
    List<PatologiaDTO> findAllDTO();
    List<String> getTermosByIdPatologia(Long id);
    PatologiaDTO findDTOById(Long idPatologia);
    List<PatologiaDTO> findByIds(List<Long> ids);
    List<PatologiaDTO> getStaticList();
}
