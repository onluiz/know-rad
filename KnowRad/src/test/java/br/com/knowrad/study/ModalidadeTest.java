package br.com.knowrad.study;

import br.com.knowrad.dao.study.ModalidadeDAO;
import br.com.knowrad.entity.study.Modalidade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/coreContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class ModalidadeTest {

    @Autowired
    private ModalidadeDAO dao;

    @Test
    public void initialTest() throws Exception {
        Modalidade modalidade = new Modalidade();
        modalidade.setModalidade("CT");
        dao.persist(modalidade);
    }

}
