package br.com.knowrad.patologia;

import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaModalidade;
import br.com.knowrad.entity.study.Modalidade;
import br.com.knowrad.service.patologia.PatologiaModalidadeService;
import br.com.knowrad.service.patologia.PatologiaService;
import br.com.knowrad.service.study.ModalidadeService;
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
public class PatologiaModalidadeTest {

    @Autowired
    private PatologiaModalidadeService service;

    @Autowired
    private PatologiaService patologiaService;

    @Autowired
    private ModalidadeService modalidadeService;

    @Test
    @Ignore
    public void persistTest() {
        Patologia p = patologiaService.findById(new Long(3));
        Modalidade m = modalidadeService.findById(new Long(1));

        PatologiaModalidade pm = new PatologiaModalidade();
        pm.setPatologia(p);
        pm.setModalidade(m);

        service.persist(pm);

        TestCase.assertNotNull(pm.getIdPatologiaModalidade());
        TestCase.assertTrue(pm.getIdPatologiaModalidade() > 0);
    }

    @Test
    @Ignore
    public void removeTest() {
        Long idPatologia = new Long(3);
        Long idModalidade = new Long(1);

        PatologiaModalidade pm = service.findByIds(idPatologia, idModalidade);

        service.remove(pm.getIdPatologiaModalidade());
    }

    @Test
    @Ignore
    public void findByIdsTest() {
        Long idPatologia = new Long(3);
        Long idModalidade = new Long(1);

        PatologiaModalidade pm = service.findByIds(idPatologia, idModalidade);

        TestCase.assertNotNull(pm);
        TestCase.assertTrue(pm.getIdPatologiaModalidade() > 0);
    }

    @Test
    @Ignore
    public void findAllByIdPatologiaTest() {
        Long idPatologia = new Long(3);

        List<PatologiaModalidade> list = service.findAllByIdPatologia(idPatologia);

        TestCase.assertNotNull(list);
        TestCase.assertTrue(list.size() > 0);
    }

}
