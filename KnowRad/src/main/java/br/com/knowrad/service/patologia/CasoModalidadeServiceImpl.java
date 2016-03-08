package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.CasoModalidadeDAO;
import br.com.knowrad.entity.patologia.CasoModalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class CasoModalidadeServiceImpl implements CasoModalidadeService {

    @Autowired
    private CasoModalidadeDAO dao;

    public void persist(CasoModalidade c) {
        dao.persist(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public CasoModalidade findByIds(Long idCaso, Long idModalidade) {
        return dao.findByIds(idCaso, idModalidade);
    }

    public List<CasoModalidade> findAllByIdCaso(Long idCaso) {
        return dao.findAllByIdCaso(idCaso);
    }

}
