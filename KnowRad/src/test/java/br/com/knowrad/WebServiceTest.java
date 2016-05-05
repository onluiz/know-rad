package br.com.knowrad;

import br.com.knowrad.dto.EdgeDTO;
import br.com.knowrad.dto.LaudoDTO;
import br.com.knowrad.dto.SearchResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.util.Util;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebServiceTest {

	List<PatologiaDTO> doencas = new ArrayList<PatologiaDTO>() {{
		add(new PatologiaDTO() {{
			setId(1);
			setNome("tuberculose");
//			setPalavras(new String[] {
//					"escavação",
//					"escavada",
//					"nodulos",
//					"intersticiais",
//					"intersticial"
//			});
		}});

//		add(new PatologiaDTO(){{
//			setId(2);
//			setNome("asma bronquiectasias");
//			setPalavras(new String[] {
//					"mosaico"
//			});
//		}});
//
//		add(new PatologiaDTO(){{
//			setId(3);
//			setNome("PH");
//			setPalavras(new String[] {
//					"mosaico",
//					"consolidações"
//			});
//		}});

	}};

	PatologiaDTO findDoencaById(Long id) {
		PatologiaDTO patologiaDTO = null;
		int count = 0;

		while(patologiaDTO == null) {
			PatologiaDTO dto = doencas.get(count);
			if(dto.getId() == id)
				patologiaDTO = dto;
			count++;
		}

		return patologiaDTO;
	}

	@Test
	@Ignore
	public void queryTest() {
		
		String search = "Nódulo";
		
		final String URL = "localhost";
		final String PORT = "8983";
		final String CONTEXT = "solr";
		final String CORE = "laudos";
		
		String urlString = "http://" + URL + ":" + PORT + "/" + CONTEXT + "/"+ CORE;
		
		SolrClient solr = new HttpSolrClient(urlString);
		
		SolrQuery query = new SolrQuery();
		query.setQuery("texto_limpo:*" + Util.cleanText(search) + "*");

		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		List<PatologiaDTO> listDoenca = new ArrayList<PatologiaDTO>();
		List<EdgeDTO> listEdge = new ArrayList<EdgeDTO>();

		try {
			
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			String teste = "";
			System.out.println(teste);
			
			for(Map solrMap : list) {
				LaudoDTO dto = new LaudoDTO();
				dto.setId(Util.verifyString(solrMap.get("id")));
				dto.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setTextoLimpo(Util.verifyString(solrMap.get("texto_limpo")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				dto.setPatologias(Util.objectToArrayListLong(solrMap.get("doencas")));

				if(dto.getPatologias().size() > 0) {
					for(Long id : dto.getPatologias()) {
						PatologiaDTO patologiaDTO = findDoencaById(id);
						if(!listDoenca.contains(patologiaDTO)) {
							listDoenca.add(patologiaDTO);
						}
//						listEdge.add(new EdgeDTO(patologiaDTO.getId(), dto.getId()));
					}
				}

				listLaudo.add(dto);
			}

			SearchResponse searchResponse = new SearchResponse();
//			searchResponse.setListDoencas(listDoenca);
//			searchResponse.setListEdges(listEdge);
//			searchResponse.setListLaudos(listLaudo);

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
