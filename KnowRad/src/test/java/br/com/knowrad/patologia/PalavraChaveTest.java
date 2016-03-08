package br.com.knowrad.patologia;

import br.com.knowrad.entity.patologia.PalavraChave;
import br.com.knowrad.service.patologia.PalavraChaveService;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/coreContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class PalavraChaveTest {

    @Autowired
    private PalavraChaveService service;

    @Test
    @Ignore
    public void persistTest() throws Exception {
        PalavraChave p = new PalavraChave();
        p.setPalavra("coluna");
        service.persist(p);
        TestCase.assertTrue(p.getIdPalavraChave() != null && p.getIdPalavraChave() > 0);
    }

    @Test
    @Ignore
    public void mergeTest() throws Exception {
        PalavraChave p = service.findById(new Long(1));
        p.setPalavra(p.getPalavra() + "-alterado");
        service.merge(p);
    }

    @Test
    @Ignore
    public void removeTest() throws Exception {
        PalavraChave p = service.findById(new Long(1));
        service.remove(p.getIdPalavraChave());
    }

    @Test
    @Ignore
    public void findByIdTest() {
        Long id = new Long(1);
        PalavraChave p = service.findById(id);
        TestCase.assertNotNull(p);
        TestCase.assertTrue(p.getIdPalavraChave() > 0);
    }

}
