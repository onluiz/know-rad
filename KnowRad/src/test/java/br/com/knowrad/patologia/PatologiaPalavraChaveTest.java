package br.com.knowrad.patologia;

import br.com.knowrad.entity.patologia.PalavraChave;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaPalavraChave;
import br.com.knowrad.service.patologia.PalavraChaveService;
import br.com.knowrad.service.patologia.PatologiaPalavraChaveService;
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
public class PatologiaPalavraChaveTest {

    @Autowired
    private PatologiaPalavraChaveService service;

    @Autowired
    private PatologiaService patologiaService;

    @Autowired
    private PalavraChaveService palavraChaveService;

    @Test
    @Ignore
    public void persistTest() {
        Long idPatologia = new Long(2);
        Long idPalavraChave = new Long(1);

        Patologia p = patologiaService.findById(idPatologia);
        PalavraChave pc = palavraChaveService.findById(idPalavraChave);

        PatologiaPalavraChave ppc = new PatologiaPalavraChave();
        ppc.setPatologia(p);
        ppc.setPalavraChave(pc);

        service.persist(ppc);

        TestCase.assertNotNull(ppc.getIdPatologiaPalavraChave());
        TestCase.assertTrue(ppc.getIdPatologiaPalavraChave() > 0);
    }

    @Test
    @Ignore
    public void removeTest() {
        Long idPatologia = new Long(2);
        Long idPalavraChave = new Long(1);

        PatologiaPalavraChave ppc = service.findByIds(idPatologia, idPalavraChave);

        service.remove(ppc.getIdPatologiaPalavraChave());
    }

    @Test
    @Ignore
    public void findByIdsTest() {
        Long idPatologia = new Long(2);
        Long idPalavraChave = new Long(1);

        PatologiaPalavraChave ppc = service.findByIds(idPatologia, idPalavraChave);

        TestCase.assertNotNull(ppc);
        TestCase.assertTrue(ppc.getIdPatologiaPalavraChave() > 0);
    }

    @Test
    @Ignore
    public void findAllByIdPatologiaTest() {
        Long idPatologia = new Long(2);

        List<PatologiaPalavraChave> list = service.findAllByIdPatologia(idPatologia);

        TestCase.assertNotNull(list);
        TestCase.assertTrue(list.size() > 0);
    }

}
