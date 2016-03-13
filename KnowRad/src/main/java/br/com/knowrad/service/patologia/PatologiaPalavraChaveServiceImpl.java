package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.PatologiaPalavraChaveDAO;
import br.com.knowrad.entity.patologia.PatologiaPalavraChave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PatologiaPalavraChaveServiceImpl implements PatologiaPalavraChaveService {

    @Autowired
    private PatologiaPalavraChaveDAO dao;

    public void persist(PatologiaPalavraChave c) {
        dao.persist(c);
    }

    public void remove(Long id) {
        dao.remove(id);
    }

    public PatologiaPalavraChave findByIds(Long idPatologia, Long idPalavraChave) {
        return dao.findByIds(idPatologia, idPalavraChave);
    }

    public List<PatologiaPalavraChave> findAllByIdPatologia(Long id) {
        return dao.findAllByIdPatologia(id);
    }
}
