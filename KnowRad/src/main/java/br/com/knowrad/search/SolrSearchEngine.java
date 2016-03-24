package br.com.knowrad.search;

import br.com.knowrad.dto.*;
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
			setPalavras(new String[]{
                    "escavação",
                    "escavada",
                    "nodulos",
                    "intersticiais",
                    "intersticial"
            });
            setSelected(Boolean.FALSE);
            setCytoscape_alias_list(new String[]{"tuberculose"});
            setCanonicalName("tuberculose");
            setSUID("1");
            setNodeType("RedWine");
            setName("tuberculose");
            setShared_name("tuberculose");
		}});

		add(new DoencaDTO(){{
			setId(2);
			setNome("asma bronquiectasias");
			setPalavras(new String[] {
					"mosaico"
			});
            setSelected(Boolean.FALSE);
            setCytoscape_alias_list(new String[]{"asma bronquiectasias"});
            setCanonicalName("asma bronquiectasias");
            setSUID("2");
            setNodeType("RedWine");
            setName("asma bronquiectasias");
            setShared_name("asma bronquiectasias");
		}});

		add(new DoencaDTO(){{
			setId(3);
			setNome("PH");
			setPalavras(new String[] {
					"mosaico",
					"consolidações"
			});
            setSelected(Boolean.FALSE);
            setCytoscape_alias_list(new String[]{"PH"});
            setCanonicalName("PH");
            setSUID("2");
            setNodeType("RedWine");
            setName("PH");
            setShared_name("PH");
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

		List<LaudoResponse> listLaudo = new ArrayList<LaudoResponse>();
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

            Double x = 4491.77880859375;
            Double y = 4647.23974609375;

			for(Map solrMap : list) {
				LaudoDTO dto = new LaudoDTO();
				dto.setId(Util.verifyString(solrMap.get("id")));
				dto.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setTextoLimpo(Util.verifyString(solrMap.get("texto_limpo")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				dto.setDoencas(Util.objectToArrayListLong(solrMap.get("doencas")));

                //referentes a tela
                String idPaciente = String.valueOf(dto.getIdPaciente());
                dto.setSelected(Boolean.FALSE);
                dto.setCytoscape_alias_list(new String[]{idPaciente});
                dto.setCanonicalName(idPaciente);
                dto.setSUID(dto.getId());
                dto.setNodeType("Cheese"); //kkkkk
                dto.setName(idPaciente);
                dto.setShared_name(idPaciente);

                /**
                 * FAZ ASSIAÇÃO DAS DOENÇAS
                 */
				if(dto.getDoencas().size() > 0) {
					for(Long id : dto.getDoencas()) {
						DoencaDTO doencaDTO = findDoencaById(id);
						if(!listDoenca.contains(doencaDTO)) {
							listDoenca.add(doencaDTO);
						}
						listEdge.add(new EdgeDTO(doencaDTO.getId(), dto.getId()));
					}
				}

                x += 100.0;
                y += 100.0;

                LaudoResponse laudoResponse = new LaudoResponse();
                laudoResponse.setData(dto);
                laudoResponse.setPosition(new Position(x, y));
                laudoResponse.setSelected(Boolean.FALSE);
				listLaudo.add(laudoResponse);
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
