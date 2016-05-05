package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PatologiaDAO;
import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilter;
import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.Termo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PatologiaServiceImpl implements PatologiaService {

    @Autowired
    private PatologiaDAO dao;

    @Autowired
    private TermoService termoService;

    public void persist(Patologia c) {
        dao.persist(c);
    }

    public void merge(Patologia c) {
        dao.merge(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public void removeFull(Long id) {
        List<Termo> listTermos = termoService.findListByIdPatologia(id);
        for(Termo termo : listTermos)
            termoService.remove(termo.getId());

        remove(id);
    }

    public Patologia findById(Long id) {
        return dao.findById(id);
    }

    public List<Patologia> findAll() {
        return dao.findAll();
    }

    public DatatableResponse<PatologiaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, PatologiaFilter filter) {
        return dao.findListDatatableByFilter(datatableRequest, filter);
    }

    public List<PatologiaDTO> findAllDTO() {
        List<Patologia> listPatologia = findAll();
        List<PatologiaDTO> listDTO = new ArrayList<PatologiaDTO>();

        for(Patologia patologia : listPatologia)
            listDTO.add(entityToDTO(patologia));

        return listDTO;
    }

    PatologiaDTO entityToDTO(Patologia patologia) {
        PatologiaDTO dto = new PatologiaDTO();
        dto.setId(patologia.getId());
        dto.setNome(patologia.getNome());
        dto.setCanonicalName(patologia.getNome());
        dto.setCytoscape_alias_list(new String[] {patologia.getNome()});
        dto.setNodeType("RedWine");
        dto.setNodeTypeFormatted("RedWine");
        dto.setName(patologia.getNome());
        dto.setPalavras(getTermosByIdPatologia(patologia.getId()));
        dto.setShared_name(patologia.getNome());
        dto.setSUID("");
        dto.setSelected(Boolean.FALSE);
        return dto;
    }

    public List<String> getTermosByIdPatologia(Long id) {
        List<TermoDTO> listDTO = termoService.findListDTOByIdPatologia(id);
        List<String> listTermos = new ArrayList<String>();

        for(TermoDTO dto : listDTO)
            listTermos.add(dto.getNomeTermo());

        return listTermos;
    }
}
