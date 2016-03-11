package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PatologiaCasoDAO;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PatologiaCasoServiceImpl implements PatologiaCasoService {

    @Autowired
    private PatologiaCasoDAO dao;

    public void persist(PatologiaCaso c) {
        dao.persist(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public PatologiaCaso findById(Long id) {
        return dao.findById(id);
    }

    public PatologiaCaso findByIds(Long idCaso, Long idPatologia) {
        return dao.findByIds(idCaso, idPatologia);
    }

    public List<PatologiaCaso> findAllByIdCaso(Long id) {
        return dao.findAllByIdCaso(id);
    }
}
