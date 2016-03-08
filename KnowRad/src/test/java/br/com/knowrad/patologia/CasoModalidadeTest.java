package br.com.knowrad.patologia;

import br.com.knowrad.dao.patologia.CasoDAO;
import br.com.knowrad.dao.patologia.CasoModalidadeDAO;
import br.com.knowrad.dao.study.ModalidadeDAO;
import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.entity.study.Modalidade;
import br.com.knowrad.service.patologia.CasoModalidadeService;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/coreContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class CasoModalidadeTest {

    @Autowired
    private CasoModalidadeDAO dao;

    @Autowired
    private CasoModalidadeService service;

    @Autowired
    private CasoDAO casoDAO;

    @Autowired
    private ModalidadeDAO modalidadeDAO;

    @Test
    @Ignore
    public void persistTest() throws Exception {
        Caso caso = casoDAO.findById(new Long(2));
        Modalidade modalidade = modalidadeDAO.findById(new Long(8));

        CasoModalidade c = new CasoModalidade();
        c.setCaso(caso);
        c.setModalidade(modalidade);
        service.persist(c);

        TestCase.assertNotNull(c.getIdCasoModalidade());
        TestCase.assertTrue(c.getIdCasoModalidade() > 0);
    }

    @Test
    @Ignore
    public void removeTest() throws Exception {
        CasoModalidade c = service.findByIds(new Long(2), new Long(8));
        service.remove(c.getIdCasoModalidade());
    }

    @Test
    @Ignore
    public void findByIdsTest() throws Exception {
        Long idCaso = new Long(2);
        Long idModalidade = new Long(8);
        CasoModalidade c = service.findByIds(idCaso, idModalidade);

        TestCase.assertNotNull(c);
        TestCase.assertTrue(c.getIdCasoModalidade() > 0);
    }

    @Test
    @Ignore
    public void findByIdCaso() {
        Long idCaso = new Long(2);
        List<CasoModalidade> list = service.findAllByIdCaso(idCaso);

        TestCase.assertNotNull(list);
        TestCase.assertTrue(list.size() > 0);
    }

}
