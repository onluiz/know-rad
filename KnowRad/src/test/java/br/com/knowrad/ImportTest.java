package br.com.knowrad;

import br.com.knowrad.entity.FakeNames;
import br.com.knowrad.entity.Laudo;
import br.com.knowrad.entity.Paciente;
import br.com.knowrad.service.FakeNamesService;
import br.com.knowrad.service.LaudoService;
import br.com.knowrad.service.PacienteService;
import br.com.knowrad.util.Util;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/coreContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class ImportTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private FakeNamesService fakeNamesService;

    @Autowired
    private LaudoService laudoService;

    private String getQuery(int limit) {
        StringBuffer sqlQuery = new StringBuffer();
        sqlQuery.append(" select ");
        sqlQuery.append(" study.study_custom3, ");
        sqlQuery.append(" study.accession_no, ");
        sqlQuery.append(" patient.pat_id, ");
        sqlQuery.append(" patient.pat_birthdate, ");
        sqlQuery.append(" study.study_datetime, ");

        sqlQuery.append(" study_laudo.texto, ");
        sqlQuery.append(" study_laudo.titulo ");

        sqlQuery.append(" FROM public.study study ");
        sqlQuery.append("   join public.patient patient on study.patient_fk = patient.pk ");
        sqlQuery.append("   join public.study_laudo study_laudo on study_laudo.id_study_laudo = (select max(sll.id_study_laudo) from study_laudo sll where status = 'PUBLICADO' and sll.id_study = study.pk) ");
        sqlQuery.append(" WHERE 1 = 1 AND study_laudo.data_registro < '2015-11-01 00:00:00' and study.study_custom3 = 'CT' and study.study_custom2 = '6400' ");
        sqlQuery.append(" ORDER BY study.study_datetime DESC ");
        sqlQuery.append(" LIMIT " + limit + " ");

        return sqlQuery.toString();
    }

    FakeNames findOne() {
        FakeNames fakeNames = null;
        boolean finish = Boolean.FALSE;
        int count = 0;

        while(!finish) {

            fakeNames = listFakeNames.get(count);
            if(!fakeNames.getUsed()) {
                fakeNames.setUsed(Boolean.TRUE);
                fakeNamesService.merge(fakeNames);
                finish = Boolean.TRUE;
            }

            count++;
        }

        return fakeNames;
    }

    List<FakeNames> listFakeNames;

    @Test
    @Ignore
    public void importStudies() {
        listFakeNames = fakeNamesService.findAll();

        Connection con = getConnection();
        Statement stm;
        ResultSet rs;
        String sql = getQuery(1500);

        int count = 1;

        if(con == null)
            return;

        try {
            stm = con.createStatement();
            if(stm == null)
                return;

            rs = stm.executeQuery(sql);
            if(rs == null)
                return;

            while(rs.next()) {
                String patId = Util.verifyString(rs.getString("pat_id"));
                if(!patId.equals("")) {

                    Date dataNascimento = Util.stringToDate(rs.getString("pat_birthdate"), "yyyyMMdd");
                    Paciente paciente = pacienteService.findByPatId(patId);
                    if(paciente == null) {
                        paciente = new Paciente();
                        paciente.setPatId(patId);
                        paciente.setNome(findOne().getNome());
                        paciente.setDataNascimento(dataNascimento);
                        pacienteService.persist(paciente);
                    }
                    else {
                        paciente.setDataNascimento(dataNascimento);
                        pacienteService.merge(paciente);
                    }

                    String accessionNo = Util.verifyString(rs.getString("accession_no"));
                    if(!accessionNo.equals("")) {

                        Laudo laudo = laudoService.findByAccessionNo(accessionNo);
                        if(laudo == null) {
                            laudo = new Laudo();
                        }
                        laudo.setPaciente(paciente);
                        laudo.setAccessionNo(accessionNo);
                        laudo.setModalidade(Util.verifyString(rs.getString("study_custom3")));
                        laudo.setStudyDate(rs.getDate("study_datetime"));
                        laudo.setTitulo(Util.verifyString(rs.getString("titulo")));
                        laudo.setTexto(Util.verifyString(rs.getString("texto")));
                        laudo.setTextoLimpo(Util.cleanText(laudo.getTexto()));
                        laudoService.persist(laudo);

                        System.out.println("LOG NÚM: " + count + "  NOVO LAUDO AN: " + accessionNo + "   PACIENTE: " + paciente.getNome());
                        count++;
                    }
                }
            }

            rs.close();
            stm.close();
            con.close();
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        String url = "jdbc:postgresql://bancotoimport.com.br:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","12345");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, props);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
