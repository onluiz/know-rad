package br.com.knowrad.service.doenca;

import br.com.knowrad.dao.doenca.DoencaDAO;
import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.dto.doenca.DoencaFilter;
import br.com.knowrad.dto.doenca.TermoDTO;
import br.com.knowrad.entity.doenca.Doenca;
import br.com.knowrad.entity.doenca.Termo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class DoencaServiceImpl implements DoencaService {

    @Autowired
    private DoencaDAO dao;

    @Autowired
    private TermoService termoService;

    public void persist(Doenca c) {
        dao.persist(c);
    }

    public void merge(Doenca c) {
        dao.merge(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public void removeFull(Long id) {
        List<Termo> listTermos = termoService.findListByIdDoenca(id);
        for(Termo termo : listTermos)
            termoService.remove(termo.getId());

        remove(id);
    }

    public Doenca findById(Long id) {
        return dao.findById(id);
    }

    public List<Doenca> findAll() {
        return dao.findAll();
    }

    public DatatableResponse<DoencaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, DoencaFilter filter) {
        return dao.findListDatatableByFilter(datatableRequest, filter);
    }

    public List<DoencaDTO> findAllDTO() {
        List<Doenca> listDoenca = new ArrayList<Doenca>();
        List<DoencaDTO> listDTO = new ArrayList<DoencaDTO>();

        for(Doenca doenca : listDoenca)
            listDTO.add(entityToDTO(doenca));

        return listDTO;
    }

    DoencaDTO entityToDTO(Doenca doenca) {
        DoencaDTO dto = new DoencaDTO();
        dto.setId(doenca.getId());
        dto.setNome(doenca.getNome());
        dto.setSelected(doenca.getSelected());
        dto.setCanonicalName(doenca.getCanonicalName());
        dto.setCytoscape_alias_list(new String[] {doenca.getCytoscape_alias_list()});
        dto.setNodeType(doenca.getNodeType());
        dto.setNodeTypeFormatted(doenca.getNodeTypeFormatted());
        dto.setName(doenca.getName());
        dto.setPalavras(getTermosByIdDoenca(doenca.getId()));
        dto.setShared_name(doenca.getShared_name());
        dto.setSUID(doenca.getSUID());
        return dto;
    }

    public List<String> getTermosByIdDoenca(Long id) {
        List<TermoDTO> listDTO = termoService.findListDTOByIdDoenca(id);
        List<String> listTermos = new ArrayList<String>();

        for(TermoDTO dto : listDTO)
            listTermos.add(dto.getNomeTermo());

        return listTermos;
    }
}
