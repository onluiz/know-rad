package br.com.knowrad.search;

import br.com.knowrad.dto.*;
import br.com.knowrad.dto.doenca.DoencaDTO;
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

	List<DoencaDTO> doencas = Util.getDoencas();

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
			String teste = "break here";
			if(dto.getId() == id)
				doencaDTO = dto;
			count++;
		}

		return doencaDTO;
	}

	public SearchResponse searchLaudos2(String search) {

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
//
//				if(dto.getIdPaciente() > 0) {
//
//					PatientResponse patientResponse = null;
//
//					for(PatientResponse p : listPatient) {
//						if(String.valueOf(dto.getIdPaciente()).equals(p.getData().getId())) {
//							patientResponse = p;
//							break;
//						}
//					}
//
////					if(patientResponse == null) {
////						PatientDTO patientDTO = new PatientDTO();
////						String idPaciente = String.valueOf(dto.getIdPaciente());
////						patientDTO.setId(idPaciente);
////						patientDTO.setSelected(Boolean.FALSE);
////						patientDTO.setCytoscape_alias_list(new String[]{idPaciente});
////						patientDTO.setCanonicalName("Pat: " + idPaciente);
////						patientDTO.setSUID(idPaciente);
////						patientDTO.setNodeType("Cheese"); //kkkkk
////						patientDTO.setName(idPaciente);
////						patientDTO.setShared_name(idPaciente);
////						patientDTO.setNodeTypeFormatted("Cheese");
////
////						patientResponse = new PatientResponse();
////						patientResponse.setData(patientDTO);
////						patientResponse.setPosition(new Position(0.0, 0.0));
////						listPatient.add(patientResponse);
////					}
//
//					EdgeDTO edgeDTO = new EdgeDTO();
//					edgeDTO.setSelected(Boolean.FALSE);
//					edgeDTO.setSource(dto.getId());
//					edgeDTO.setTarget(String.valueOf(dto.getIdPaciente()));
//					edgeDTO.setCanonicalName(dto.getCanonicalName() + " " + dto.getIdPaciente());
//					edgeDTO.setSUID(edgeDTO.getId());
//					edgeDTO.setName(edgeDTO.getCanonicalName());
//					edgeDTO.setInteraction("cc");
//					edgeDTO.setShared_interaction("cc");
//					edgeDTO.setShared_name(edgeDTO.getCanonicalName());
//
//					EdgeResponse edgeResponse = new EdgeResponse();
//					edgeResponse.setData(edgeDTO);
//					edgeResponse.setSelected(Boolean.FALSE);
//
//					listEdge.add(edgeResponse);
//				}

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
