package br.com.knowrad.search;

import br.com.knowrad.dto.doenca.TermoDTO;
import br.com.knowrad.dto.patologia.LaudoDTO;
import br.com.knowrad.entity.FakeNames;
import br.com.knowrad.entity.Paciente;
import br.com.knowrad.service.FakeNamesService;
import br.com.knowrad.service.PacienteService;
import br.com.knowrad.service.doenca.TermoService;
import br.com.knowrad.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
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
    private FakeNamesService fakeNamesService;

    List<TermoDTO> listTermoDTO;
    List<FakeNames> listFakeNames;

    public void indexar() {

        listTermoDTO = termoService.findAllDTO();
        listFakeNames = fakeNamesService.findAll();
        /**
         * Realiza a leitura de um arquivo JSON com os modelos de laudo
         */
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("laudos.json");
        List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();

        try {

            String content = IOUtils.toString(inputStream, "UTF-8");

            JsonElement jelement = new JsonParser().parse(content);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("listLaudos");

            String nomePaciente = "PACIENTE";

            for(int i = 0; i < jarray.size(); i++) {

                JsonObject item = jarray.get(i).getAsJsonObject();

                if(item != null) {
                    LaudoDTO dto = new LaudoDTO();
                    dto.setIdPaciente(Util.verifyLongJson(item.get("idpaciente")));
                    dto.setNomePaciente(nomePaciente + i);
                    dto.setTitulo(Util.verifyStringJson(item.get("titulo")));
                    dto.setTexto(Util.verifyStringJson(item.get("texto")));
                    dto.setTextoLimpo(Util.cleanText(Util.verifyStringJson(item.get("texto"))));
                    dto.setModalidade(Util.verifyStringJson(item.get("modalidade")));
                    dto.setDoencas(procurarDoencas(dto.getTextoLimpo()));
                    listLaudo.add(dto);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Indexa laudos coletados do arquivo json
         */
        String urlString = "http://localhost:8983/solr/laudos";
        SolrClient solr = new HttpSolrClient(urlString);

        try {

            for(LaudoDTO laudoDTO : listLaudo) {
                Paciente paciente = managePaciente(Util.verifyString(laudoDTO.getIdPaciente()));

                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", laudoDTO.getIdPaciente());
                document.addField("id_paciente", paciente.getId());
                document.addField("nome_paciente", paciente.getNome());
                document.addField("titulo", laudoDTO.getTitulo());
                document.addField("texto", laudoDTO.getTexto());
                document.addField("texto_limpo", laudoDTO.getTextoLimpo());
                document.addField("modalidade", laudoDTO.getModalidade());
                document.addField("doencas", laudoDTO.getDoencas());
                try {
                    solr.add(document);
                } catch(HttpSolrClient.RemoteSolrException e) {
                }

                System.out.println("NOVO LAUDO INDEXADO: " + laudoDTO.getIdPaciente());
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

    Paciente managePaciente(String patId) {
        Paciente paciente = pacienteService.findByPatId(patId);
        if(paciente == null) {
            paciente = new Paciente();
            paciente.setPatId(patId);
            paciente.setNome(findOne().getNome());
            pacienteService.persist(paciente);

            System.out.println("NOVO PACIENTE ADICIONADO: " + paciente.getNome());
        }
        else {
            System.out.println("NOVO PACIENTE ENCONTRADO: " + paciente.getNome());
        }
        return paciente;
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

    List<Long> procurarDoencas(String texto) {

        texto = Util.cleanText(texto);

        List<Long> list = new ArrayList<Long>();

        for (TermoDTO termoDTO : listTermoDTO) {

            if (texto.indexOf(Util.cleanText(termoDTO.getNomeTermo())) > -1) {

                boolean achou = false;
                for (Long idEncontrado : list) {
                    if (idEncontrado == termoDTO.getIdDoenca()) {
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    System.out.println("ACHOU: " + termoDTO.getNomeTermo());
                    list.add(termoDTO.getIdDoenca());
                }
            }

        }

        return list;
    }

}
