package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PatologiaDAO;
import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilterDTO;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import br.com.knowrad.entity.patologia.PatologiaModalidade;
import br.com.knowrad.entity.patologia.PatologiaPalavraChave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PatologiaServiceImpl implements PatologiaService {

    @Autowired
    private PatologiaDAO dao;

    @Autowired
    private PatologiaModalidadeService patologiaModalidadeService;

    @Autowired
    private PatologiaPalavraChaveService patologiaPalavraChaveService;

    @Autowired
    private PatologiaCasoService patologiaCasoService;

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
        //implementar removeFull das patologias

        //patologia modalidade
        List<PatologiaModalidade> listPatologiaModalidade = patologiaModalidadeService.findAllByIdPatologia(id);
        for(PatologiaModalidade patologiaModalidade : listPatologiaModalidade)
            patologiaModalidadeService.remove(patologiaModalidade.getIdPatologiaModalidade());

        //patologia palavra chave
        List<PatologiaPalavraChave> listPatologiaPalavraChave = patologiaPalavraChaveService.findAllByIdPatologia(id);
        for(PatologiaPalavraChave patologiaPalavraChave : listPatologiaPalavraChave)
            patologiaPalavraChaveService.remove(patologiaPalavraChave.getIdPatologiaPalavraChave());

        //patologia caso
        List<PatologiaCaso> listPatologiaCaso = patologiaCasoService.findAllByIdCaso(id);
        for(PatologiaCaso patologiaCaso : listPatologiaCaso)
            patologiaCasoService.remove(patologiaCaso.getIdPatologiaCaso());

        //remover patologia
        Patologia patologia = findById(id);
        remove(patologia.getIdPatologia());
    }

    public Patologia findById(Long id) {
        return dao.findById(id);
    }

    public PatologiaDTO findDTOById(Long id) {
        return entityToDto(findById(id));
    }

    public DatatableResponse<PatologiaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, PatologiaFilterDTO filter) {
        return dao.findListDatatableByFilter(datatableRequest, filter);
    }

    public List<PatologiaDTO> search(String searchText, Integer limit) {
        return dao.search(searchText, limit);
    }

    PatologiaDTO entityToDto(Patologia patologia) {
        PatologiaDTO dto = new PatologiaDTO();
        dto.setIdPatologia(patologia.getIdPatologia());
        dto.setDescricao(patologia.getDescricao());
        return dto;
    }
}
