package br.com.knowrad.search;

import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.Laudo;
import br.com.knowrad.service.LaudoService;
import br.com.knowrad.service.PacienteService;
import br.com.knowrad.service.patologia.TermoService;
import br.com.knowrad.util.Util;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SolrIndexerEngine {

    @Autowired
    private TermoService termoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private LaudoService laudoService;

    List<TermoDTO> listTermoDTO;

    public void indexar() {

        listTermoDTO = termoService.findAllDTO();
        String urlString = "http://localhost:8983/solr/laudos";
        SolrClient solr = new HttpSolrClient(urlString);

        try {

            List<Laudo> listLaudos = laudoService.findAll();
            for(Laudo laudo : listLaudos) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", laudo.getAccessionNo());
                document.addField("id_paciente", laudo.getPaciente().getPatId());
                document.addField("pat_id", laudo.getPaciente().getPatId());
                document.addField("nome_paciente", laudo.getPaciente().getNome());
                document.addField("idade_paciente", Util.calcIdade(laudo.getPaciente().getDataNascimento()));
                document.addField("data_nascimento", Util.verifyString(laudo.getPaciente().getDataNascimento()));
                document.addField("data_nascimento_format", Util.formatDate(laudo.getPaciente().getDataNascimento(), "dd/MM/yyyy"));
                document.addField("data_exame", Util.verifyString(laudo.getStudyDate()));
                document.addField("data_exame_format", Util.formatDate(laudo.getStudyDate(), "dd/MM/yyyy"));
                document.addField("titulo", laudo.getTitulo());
                document.addField("texto", laudo.getTexto());
                document.addField("texto_limpo", laudo.getTextoLimpo());
                document.addField("modalidade", laudo.getModalidade());
                document.addField("patologias", procurarPatologias(laudo.getTextoLimpo()));
                try {
                    solr.add(document);
                } catch(HttpSolrClient.RemoteSolrException e) {
                }

                System.out.println("NOVO LAUDO INDEXADO: " + laudo.getId());
            }
            solr.commit();
            solr.close();
            System.out.println("FIM DA INDEXAÇÃO");

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Long> procurarPatologias(String texto) {

        List<Long> list = new ArrayList<Long>();

        for (TermoDTO termoDTO : listTermoDTO) {

            if (texto.indexOf(Util.cleanText(termoDTO.getNomeTermo())) > -1) {

                boolean achou = false;
                for (Long idEncontrado : list) {
                    if (idEncontrado == termoDTO.getIdPatologia()) {
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    System.out.println("TERMO: " + termoDTO.getNomeTermo());
                    list.add(termoDTO.getIdPatologia());
                }
            }

        }

        return list;
    }

}
