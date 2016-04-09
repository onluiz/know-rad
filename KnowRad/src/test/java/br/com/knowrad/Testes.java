package br.com.knowrad;

import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.util.Util;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testes {

    List<DoencaDTO> doencas = new ArrayList<DoencaDTO>() {{
//        add(new DoencaDTO() {{
//            setId(1);
//            setNome("tuberculose");
//            setPalavras(new String[] {
//                    "escavação",
//                    "escavada",
//                    "nodulos",
//                    "intersticiais",
//                    "intersticial"
//            });
//        }});
//
//        add(new DoencaDTO(){{
//            setId(2);
//            setNome("asma bronquiectasias");
//            setPalavras(new String[] {
//                    "mosaico"
//            });
//        }});
//
//        add(new DoencaDTO(){{
//            setId(3);
//            setNome("PH");
//            setPalavras(new String[] {
//                    "mosaico",
//                    "consolidações"
//            });
//        }});
    }};

    void print(String v) {
        System.out.println(v);
    }

    @Test
    @Ignore
    public void main() {

        String teste = "Nódulos encontrado na gripe";

        teste = Util.cleanText(teste);

        List<Long> listDoencasEncontradas = new ArrayList<Long>();

        for(DoencaDTO doenca : doencas) {

            for(String palavra : doenca.getPalavras()) {

                if(teste.indexOf(Util.cleanText(palavra)) > -1) {
                    print("ACHOU");
                    print("Doença: " + doenca.getNome());
                    listDoencasEncontradas.add(doenca.getId());
                }

            }

        }

        long[] idsDoencas = new long[listDoencasEncontradas.size()];

        String teste2 = "";

    }

    class Doenca {
        public long id;
        public String nome;
        public String[] palavras;
    }

}
