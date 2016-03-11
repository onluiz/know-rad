package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.CasoDAO;
import br.com.knowrad.dto.CasoDTO;
import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class CasoServiceImpl implements CasoService {

    @Autowired
    private CasoDAO dao;

    @Autowired
    private CasoModalidadeService casoModalidadeService;

    @Autowired
    private PatologiaCasoService patologiaCasoService;

    public void persist(Caso c) {
        dao.persist(c);
    }

    public void merge(Caso c) {
        dao.merge(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public void removeFull(Long idCaso) {
        List<CasoModalidade> listCasoModalidade = casoModalidadeService.findAllByIdCaso(idCaso);
        for(CasoModalidade casoModalidade : listCasoModalidade)
            casoModalidadeService.remove(casoModalidade.getIdCasoModalidade());

        List<PatologiaCaso> listPatologiaCaso = patologiaCasoService.findAllByIdCaso(idCaso);
        for(PatologiaCaso patologiaCaso : listPatologiaCaso)
            patologiaCasoService.remove(patologiaCaso.getIdPatologiaCaso());

        Caso caso = findById(idCaso);
        if(caso != null)
            remove(caso.getIdCaso());
    }

    public Caso findById(Long id) {
        return dao.findById(id);
    }

    public List<Caso> findAll() {
        return dao.findAll();
    }

    public List<CasoDTO> findAllDTO() {
        List<CasoDTO> listCasoDTO = new ArrayList<CasoDTO>();
        for(Caso caso : findAll())
            listCasoDTO.add(dtoToEntity(caso));
        return listCasoDTO;
    }

    CasoDTO dtoToEntity(Caso caso) {
        CasoDTO dto = new CasoDTO();
        dto.setIdCaso(caso.getIdCaso());
        dto.setLaudo(caso.getLaudo());
        dto.setTitulo(caso.getTitulo());
        return dto;
    }
}
