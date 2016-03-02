package br.com.knowrad;

import br.com.knowrad.dao.study.ModalidadeDAO;
import br.com.knowrad.entity.study.Modalidade;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/coreContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class InitSystem {

    @Autowired
    private ModalidadeDAO modalidadeDAO;

    @Test
    @Ignore
    public void init() {
        initModalidades();
    }

    void initModalidades() {
        for(String chave : new ArrayList<String>() {{
            add("OT");
            add("SC");
            add("CR");
            add("MR");
            add("US");
            add("MG");
            add("RX");
            add("CT");
        }}) modalidadeDAO.persist(new Modalidade(chave));
    }

}
