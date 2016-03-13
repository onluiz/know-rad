package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PatologiaModalidadeDAO;
import br.com.knowrad.entity.patologia.PatologiaModalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PatologiaModalidadeServiceImpl implements PatologiaModalidadeService {

    @Autowired
    private PatologiaModalidadeDAO dao;

    public void persist(PatologiaModalidade c) {
        dao.persist(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public PatologiaModalidade findByIds(Long idPatologia, Long idModalidade) {
        return dao.findByIds(idPatologia, idModalidade);
    }

    public List<PatologiaModalidade> findAllByIdPatologia(Long id) {
        return dao.findAllByIdPatologia(id);
    }
}
