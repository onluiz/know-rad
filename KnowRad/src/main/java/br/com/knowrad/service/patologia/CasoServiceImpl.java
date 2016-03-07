package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.CasoDAO;
import br.com.knowrad.entity.patologia.Caso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class CasoServiceImpl implements CasoService {

    @Autowired
    private CasoDAO casoDAO;

    public void persist(Caso c) {
        casoDAO.persist(c);
    }
}
