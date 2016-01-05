package br.com.knowrad;

import br.com.knowrad.util.Util;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AjudstsOnPatologies {

    @Test
    public void adjustListPatologiesCase() {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("casos-patologia.txt");

        try {

            String content = IOUtils.toString(inputStream);
            String contents[] = content.split("####INICIOLAUDO####");
            List<PatologiaCase> list = new ArrayList<PatologiaCase>();

            for(int i = 0; i < contents.length; i++) {

                String itens[] = contents[i].split("####SEPARADOR####");

                Patologia patologia = new Patologia(Util.verifyString(itens[0]), "CT");
                List<Patologia> listPatologias = new ArrayList<Patologia>();
                listPatologias.add(patologia);

                PatologiaCase casoPatologia = new PatologiaCase();
                casoPatologia.setListPatologia(listPatologias);
                casoPatologia.setTituloLaudo(Util.verifyString(itens[1]));
                casoPatologia.setModalidadeLaudo("CT");
                casoPatologia.setTextoLaudo(Util.verifyString(itens[2]).replaceAll("\\n", "<br>"));
                list.add(casoPatologia);
            }

            String teste = "";

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class PatologiaCase {

        private List<Patologia> listPatologia;
        private String tituloLaudo;
        private String modalidadeLaudo;
        private String textoLaudo;

        public List<Patologia> getListPatologia() {
            return listPatologia;
        }

        public void setListPatologia(List<Patologia> listPatologia) {
            this.listPatologia = listPatologia;
        }

        public String getTituloLaudo() {
            return tituloLaudo;
        }

        public void setTituloLaudo(String tituloLaudo) {
            this.tituloLaudo = tituloLaudo;
        }

        public String getModalidadeLaudo() {
            return modalidadeLaudo;
        }

        public void setModalidadeLaudo(String modalidadeLaudo) {
            this.modalidadeLaudo = modalidadeLaudo;
        }

        public String getTextoLaudo() {
            return textoLaudo;
        }

        public void setTextoLaudo(String textoLaudo) {
            this.textoLaudo = textoLaudo;
        }
    }

    public class Patologia {

        private String descricao;
        private String modalidade;

        public Patologia() {
        }

        public Patologia(String descricao, String modalidade) {
            this.descricao = descricao;
            this.modalidade = modalidade;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getModalidade() {
            return modalidade;
        }

        public void setModalidade(String modalidade) {
            this.modalidade = modalidade;
        }
    }

}
