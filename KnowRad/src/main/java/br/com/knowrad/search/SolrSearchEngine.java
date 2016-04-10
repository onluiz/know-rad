package br.com.knowrad.search;

import br.com.knowrad.dto.*;
import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.dto.patologia.LaudoDTO;
import br.com.knowrad.service.doenca.DoencaService;
import br.com.knowrad.util.SolrConnection;
import br.com.knowrad.util.Util;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SolrSearchEngine {

	@Autowired
	private DoencaService doencaService;

	private SolrClient solr = new SolrConnection().getSolrConnection();

	List<DoencaDTO> doencas = new ArrayList<DoencaDTO>();

	public String searchLaudosById(String idSearch) {

		doencas = doencaService.findAllDTO();

		if(solr == null)
			return "";

		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("id:" + idSearch);
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			for(Map solrMap : list) {
				return Util.verifyString(solrMap.get("texto"));
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";

	}

	public LaudoDTO searchLaudoDTOById(String idSearch) {

		doencas = doencaService.findAllDTO();

		if(solr == null)
			return null;

		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("id:" + idSearch);
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			for(Map solrMap : list) {
				LaudoDTO dto = new LaudoDTO();
				dto.setId(Util.verifyString(solrMap.get("id")));
				dto.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				dto.setDoencas(Util.objectToArrayListLong(solrMap.get("doencas")));
				return dto;
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<LaudoDTO> searchLaudos(String search) {

		doencas = doencaService.findAllDTO();

		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		
		if(solr == null)
			return listLaudo;
		
		try {

			SolrQuery query = new SolrQuery();
			query.setRows(100000);
			if(search == null || search.equals(""))
				query.setQuery("*:*");
			else {
				StringBuilder stringQuery = new StringBuilder();
				stringQuery.append("texto_limpo:*" + Util.cleanText(search) + "*");
				stringQuery.append(" OR ");
				stringQuery.append("id:" + Util.cleanText(search));
				query.setQuery(stringQuery.toString());
			}

			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			Long idLaudo = new Long(0);
			
			for(Map solrMap : list) {

				LaudoDTO dto = new LaudoDTO();
				dto.setId(Util.verifyString(solrMap.get("id")));
				dto.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				dto.setDoencas(Util.objectToArrayListLong(solrMap.get("doencas")));

				if(dto.getDoencas().size() > 0) {
					List<DoencaDTO> listDoencaDTO = new ArrayList<DoencaDTO>();
					for(Long idDoenca : dto.getDoencas())
						listDoencaDTO.add(findDoencaById(idDoenca));

					dto.setListDoencasDTO(listDoencaDTO);
				}

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

		while(doencaDTO == null && count <= doencas.size()) {
			DoencaDTO dto = doencas.get(count);
			if(dto.getId() == id)
				doencaDTO = dto;
			count++;
		}

		return doencaDTO;
	}

	public SearchResponse searchLaudos2(String search) {

		doencas = doencaService.findAllDTO();

		List<LaudoResponse> listLaudo = new ArrayList<LaudoResponse>();
		List<DoencaResponse> listDoenca = new ArrayList<DoencaResponse>();
		List<EdgeResponse> listEdge = new ArrayList<EdgeResponse>();
//		List<PatientResponse> listPatient = new ArrayList<PatientResponse>();

		if(solr == null)
			return null;

		try {

			SolrQuery query = new SolrQuery();
			query.setRows(100000);
			if(search == null || search.equals(""))
				query.setQuery("*:*");
			else
				query.setQuery("texto_limpo:*" + Util.cleanText(search) + "*");

			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();

            Double x = 4491.77880859375;
            Double y = 4647.23974609375;

			Double xDoenca = 1391.1080322265625;
			Double yDoenca = 4324.1015625;

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
                String titulo = dto.getTitulo();
                dto.setSelected(Boolean.FALSE);
                dto.setCytoscape_alias_list(new String[]{titulo});
                dto.setCanonicalName(titulo);
                dto.setSUID(dto.getId());
                dto.setNodeType("Cheese"); //kkkkk
                dto.setName(titulo);
                dto.setShared_name(titulo);
				dto.setNodeTypeFormatted("Cheese");

				/**
                 * FAZ ASSOCIAÇÃO DAS DOENÇAS
                 */
				if(dto.getDoencas().size() > 0) {
					for(Long id : dto.getDoencas()) {
						DoencaDTO doencaDTO = findDoencaById(id);

						boolean contains = Boolean.FALSE;
						for(DoencaResponse d : listDoenca) {
							if(d.getData().getId() == doencaDTO.getId()) {
								contains = Boolean.TRUE;
								break;
							}
						}

						if(!contains) {
							xDoenca += 100.0;
							yDoenca += 100.0;

							DoencaResponse doencaResponse = new DoencaResponse();
							doencaResponse.setData(doencaDTO);
							doencaResponse.setPosition(new Position(xDoenca, yDoenca));
							doencaResponse.setSelected(Boolean.FALSE);
							listDoenca.add(doencaResponse);
						}

						EdgeDTO edgeDTO = new EdgeDTO();
						edgeDTO.setSelected(Boolean.FALSE);
						edgeDTO.setSource(dto.getId());
						edgeDTO.setTarget(String.valueOf(doencaDTO.getId()));
						edgeDTO.setCanonicalName(dto.getCanonicalName() + " " + doencaDTO.getCanonicalName());
						edgeDTO.setSUID(edgeDTO.getId());
						edgeDTO.setName(edgeDTO.getCanonicalName());
						edgeDTO.setInteraction("cc");
						edgeDTO.setShared_interaction("cc");
						edgeDTO.setShared_name(edgeDTO.getCanonicalName());

						EdgeResponse edgeResponse = new EdgeResponse();
						edgeResponse.setData(edgeDTO);
						edgeResponse.setSelected(Boolean.FALSE);

						listEdge.add(edgeResponse);
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
//			searchResponse.setListPatient(listPatient);

			return searchResponse;

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
}
