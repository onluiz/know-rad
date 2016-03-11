package br.com.knowrad.patologia;

import br.com.knowrad.dto.PatologiaDTO;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.service.patologia.PatologiaService;
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
public class PatologiaTest {

    @Autowired
    private PatologiaService service;

    @Test
    @Ignore
    public void persistTest() throws Exception {
        Patologia p = new Patologia();
        p.setDescricao("descricao geral da patologia3");
        service.persist(p);

        TestCase.assertNotNull(p);
        TestCase.assertTrue(p.getIdPatologia() > 0);
    }

    @Test
    @Ignore
    public void mergeTest() throws Exception {
        Patologia p = service.findById(new Long(1));
        p.setDescricao(p.getDescricao() + "-alterado");
        service.merge(p);
    }

    @Test
    @Ignore
    public void removeTest() throws Exception {
        Patologia p = service.findById(new Long(1));
        service.remove(p.getIdPatologia());
    }

    @Test
    @Ignore
    public void findByIdTest() throws Exception {
        Long id = new Long(1);
        Patologia p = service.findById(id);

        TestCase.assertNotNull(p);
        TestCase.assertTrue(p.getIdPatologia() > 0);
    }

    @Test
    @Ignore
    public void searchTest() throws Exception {
        String text = "222222222222222222";
        List<PatologiaDTO> list = service.search(text, 100);

        TestCase.assertNotNull(list);
        TestCase.assertTrue(list.size() > 0);
    }

}
