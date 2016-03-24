package br.com.knowrad.search;

import br.com.knowrad.dto.DoencaDTO;
import br.com.knowrad.dto.EdgeDTO;
import br.com.knowrad.dto.SearchResponse;
import br.com.knowrad.dto.patologia.LaudoDTO;
import br.com.knowrad.util.SolrConnection;
import br.com.knowrad.util.Util;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolrSearchEngine {

	private SolrClient solr = new SolrConnection().getSolrConnection();

	List<DoencaDTO> doencas = new ArrayList<DoencaDTO>() {{
		add(new DoencaDTO() {{
			setId(1);
			setNome("tuberculose");
			setPalavras(new String[] {
					"escavação",
					"escavada",
					"nodulos",
					"intersticiais",
					"intersticial"
			});
		}});

		add(new DoencaDTO(){{
			setId(2);
			setNome("asma bronquiectasias");
			setPalavras(new String[] {
					"mosaico"
			});
		}});

		add(new DoencaDTO(){{
			setId(3);
			setNome("PH");
			setPalavras(new String[] {
					"mosaico",
					"consolidações"
			});
		}});

	}};

	@SuppressWarnings("rawtypes")
	public List<LaudoDTO> searchLaudos(String search) {

		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		
		if(solr == null)
			return listLaudo;
		
		try {
			
			SolrQuery query = new SolrQuery();

			if(search == null || search.equals(""))
                query.setQuery("*:*");
            else
                query.setQuery("_text_:" + search);
			
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			Long idLaudo = new Long(0);
			
			for(Map solrMap : list) {

				LaudoDTO dto = new LaudoDTO();
//				dto.setIdLaudo("laudo" + idLaudo);
				dto.setIdPaciente(Util.verifyLong(solrMap.get("idPaciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nomePaciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				listLaudo.add(dto);

				idLaudo++;
			}
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listLaudo;
		
	}

	DoencaDTO findDoencaById(Long id) {
		DoencaDTO doencaDTO = null;
		int count = 0;

		while(doencaDTO == null) {
			DoencaDTO dto = doencas.get(count);
			if(dto.getId() == id)
				doencaDTO = dto;
			count++;
		}

		return doencaDTO;
	}

	public SearchResponse searchLaudos2(String search) {

		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		List<DoencaDTO> listDoenca = new ArrayList<DoencaDTO>();
		List<EdgeDTO> listEdge = new ArrayList<EdgeDTO>();

		if(solr == null)
			return null;

		try {

			SolrQuery query = new SolrQuery();

			if(search == null || search.equals(""))
				query.setQuery("*:*");
			else
				query.setQuery("texto_limpo:*" + Util.cleanText(search) + "*");

			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();

			for(Map solrMap : list) {
				LaudoDTO dto = new LaudoDTO();
				dto.setIdIndex(Util.verifyString(solrMap.get("id")));
				dto.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setTextoLimpo(Util.verifyString(solrMap.get("texto_limpo")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				dto.setDoencas(Util.objectToArrayListLong(solrMap.get("doencas")));

				if(dto.getDoencas().size() > 0) {
					for(Long id : dto.getDoencas()) {
						DoencaDTO doencaDTO = findDoencaById(id);
						if(!listDoenca.contains(doencaDTO)) {
							listDoenca.add(doencaDTO);
						}
						listEdge.add(new EdgeDTO(doencaDTO.getId(), dto.getIdIndex()));
					}
				}

				listLaudo.add(dto);
			}

			SearchResponse searchResponse = new SearchResponse();
			searchResponse.setListDoencas(listDoenca);
			searchResponse.setListEdges(listEdge);
			searchResponse.setListLaudos(listLaudo);

			return searchResponse;

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
}
