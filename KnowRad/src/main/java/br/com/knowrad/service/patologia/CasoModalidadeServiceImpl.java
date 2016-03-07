package br.com.knowrad.service.patologia;

import br.com.knowrad.dao.patologia.CasoModalidadeDAO;
import br.com.knowrad.entity.patologia.CasoModalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class CasoModalidadeServiceImpl implements CasoModalidadeService {

    @Autowired
    private CasoModalidadeDAO casoModalidadeDAO;

    public void persist(CasoModalidade c) {
        casoModalidadeDAO.persist(c);
    }

}
