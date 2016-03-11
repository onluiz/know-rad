package br.com.knowrad.patologia;

import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import br.com.knowrad.service.patologia.CasoService;
import br.com.knowrad.service.patologia.PatologiaCasoService;
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
public class PatologiaCasoTest {

    @Autowired
    private PatologiaCasoService service;

    @Autowired
    private PatologiaService patologiaService;

    @Autowired
    private CasoService casoService;

    @Test
    @Ignore
    public void persistTest() {
        Patologia p = patologiaService.findById(new Long(2));
        Caso c = casoService.findById(new Long(106));
        PatologiaCaso pc = new PatologiaCaso();
        pc.setPatologia(p);
        pc.setCaso(c);

        service.persist(pc);
    }

    @Test
    @Ignore
    public void removeTest() {
        Long id = new Long(1);
        PatologiaCaso p = service.findById(id);
        service.remove(p.getIdPatologiaCaso());
    }

    @Test
    @Ignore
    public void findByIdTest() {
        Long id = new Long(1);
        PatologiaCaso p = service.findById(id);

        TestCase.assertNotNull(p);
        TestCase.assertTrue(p.getIdPatologiaCaso() > 0);
    }

    @Test
    @Ignore
    public void findByIdCasoTest() {
        Long idCaso = new Long(102);
        List<PatologiaCaso> list = service.findAllByIdCaso(idCaso);

        TestCase.assertNotNull(list);
        TestCase.assertTrue(list.size() > 0);
    }

    @Test
    @Ignore
    public void findByIdsTest() {
        Long idPatologia = new Long(2);
        Long idCaso = new Long(106);
        PatologiaCaso p = service.findByIds(idCaso, idPatologia);

        TestCase.assertNotNull(p);
        TestCase.assertTrue(p.getIdPatologiaCaso() > 0);
    }

}
