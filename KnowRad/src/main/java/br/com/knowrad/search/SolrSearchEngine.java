package br.com.knowrad.search;

import br.com.knowrad.dto.*;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaResponse;
import br.com.knowrad.service.PacienteService;
import br.com.knowrad.service.patologia.PatologiaService;
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
	private PatologiaService patologiaService;

	@Autowired
	private PacienteService pacienteService;

	private SolrClient solr = new SolrConnection().getSolrConnection();

	List<PatologiaDTO> patologias = new ArrayList<PatologiaDTO>();

	public String searchLaudosById(String idSearch) {

		patologias = patologiaService.findAllDTO();

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

		patologias = patologiaService.findAllDTO();

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
				dto.setPatologias(Util.objectToArrayListLong(solrMap.get("patologias")));
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

		patologias = patologiaService.findAllDTO();

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
				dto.setPatologias(Util.objectToArrayListLong(solrMap.get("patologias")));

				if(dto.getPatologias().size() > 0) {
					List<PatologiaDTO> listPatologiaDTO = new ArrayList<PatologiaDTO>();
					for(Long idPatologia : dto.getPatologias())
						listPatologiaDTO.add(findPatologiaById(idPatologia));

					dto.setListPatologiasDTO(listPatologiaDTO);
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

	PatologiaDTO findPatologiaById(Long id) {
		PatologiaDTO patologiaDTO = null;
		int count = 0;

		while(patologiaDTO == null && count <= patologias.size()) {
			PatologiaDTO dto = patologias.get(count);
			if(dto.getId() == id)
				patologiaDTO = dto;
			count++;
		}

		return patologiaDTO;
	}

	public SearchResponse searchLaudos2(String search) {

		patologias = patologiaService.findAllDTO();

		List<LaudoResponse> listLaudo = new ArrayList<LaudoResponse>();
		List<PatologiaResponse> listPatologia = new ArrayList<PatologiaResponse>();
		List<PacienteResponse> listPaciente = new ArrayList<PacienteResponse>();
		List<EdgeResponse> listEdge = new ArrayList<EdgeResponse>();
//		List<PatientResponse> listPatient = new ArrayList<PatientResponse>();

		if(solr == null)
			return null;

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

            Double x = 4491.77880859375;
            Double y = 4647.23974609375;

			Double xPatologia = 1391.1080322265625;
			Double yPatologia = 4324.1015625;

			for(Map solrMap : list) {
				LaudoDTO laudoDTO = new LaudoDTO();
				laudoDTO.setId(Util.verifyString(solrMap.get("id")));
				laudoDTO.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				laudoDTO.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				laudoDTO.setTitulo(Util.verifyString(solrMap.get("titulo")));
				laudoDTO.setTexto(Util.verifyString(solrMap.get("texto")));
				laudoDTO.setTextoLimpo(Util.verifyString(solrMap.get("texto_limpo")));
				laudoDTO.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				laudoDTO.setPatologias(Util.objectToArrayListLong(solrMap.get("patologias")));

                //referentes a tela
                String titulo = laudoDTO.getTitulo();
				laudoDTO.setSelected(Boolean.FALSE);
				laudoDTO.setCytoscape_alias_list(new String[]{titulo});
				laudoDTO.setCanonicalName(titulo);
				laudoDTO.setSUID(laudoDTO.getId());
				laudoDTO.setNodeType("Cheese");
				laudoDTO.setName(titulo);
				laudoDTO.setShared_name(titulo);
				laudoDTO.setNodeTypeFormatted("Cheese");

				PacienteDTO pacienteDTO = pacienteService.findDTOById(laudoDTO.getIdPaciente());

				PacienteResponse pacienteResponse = new PacienteResponse();
				pacienteResponse.setData(pacienteDTO);
				pacienteResponse.setPosition(new Position(100.0, 100.0));
				pacienteResponse.setSelected(Boolean.FALSE);
				listPaciente.add(pacienteResponse);

				EdgeDTO edgePacienteDTO = new EdgeDTO();
				edgePacienteDTO.setSource(laudoDTO.getId());
				edgePacienteDTO.setTarget(Util.verifyString(pacienteDTO.getId()));
				edgePacienteDTO.setSelected(Boolean.FALSE);
				edgePacienteDTO.setCanonicalName(laudoDTO.getCanonicalName() + " " + pacienteDTO.getCanonicalName());
				edgePacienteDTO.setSUID(edgePacienteDTO.getId());
				edgePacienteDTO.setName(edgePacienteDTO.getCanonicalName());
				edgePacienteDTO.setInteraction("cc");
				edgePacienteDTO.setShared_interaction("cc");
				edgePacienteDTO.setShared_name(edgePacienteDTO.getCanonicalName());

				EdgeResponse edgePacienteResponse = new EdgeResponse();
				edgePacienteResponse.setData(edgePacienteDTO);
				edgePacienteResponse.setSelected(Boolean.FALSE);

				listEdge.add(edgePacienteResponse);

				/**
                 * FAZ ASSOCIAÇÃO DAS DOENÇAS
                 */
				if(laudoDTO.getPatologias().size() > 0) {
					for(Long id : laudoDTO.getPatologias()) {
						PatologiaDTO patologiaDTO = findPatologiaById(id);

						boolean contains = Boolean.FALSE;
						for(PatologiaResponse d : listPatologia) {
							if(d.getData().getId() == patologiaDTO.getId()) {
								contains = Boolean.TRUE;
								break;
							}
						}

						if(!contains) {
							xPatologia += 100.0;
							yPatologia += 100.0;

							PatologiaResponse patologiaResponse = new PatologiaResponse();
							patologiaResponse.setData(patologiaDTO);
							patologiaResponse.setPosition(new Position(xPatologia, yPatologia));
							patologiaResponse.setSelected(Boolean.FALSE);
							listPatologia.add(patologiaResponse);
						}

						EdgeDTO edgeDTO = new EdgeDTO();
						edgeDTO.setSelected(Boolean.FALSE);
						edgeDTO.setSource(laudoDTO.getId());
						edgeDTO.setTarget(String.valueOf(patologiaDTO.getId()));
						edgeDTO.setCanonicalName(laudoDTO.getCanonicalName() + " " + patologiaDTO.getCanonicalName());
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
                laudoResponse.setData(laudoDTO);
                laudoResponse.setPosition(new Position(x, y));
                laudoResponse.setSelected(Boolean.FALSE);
				listLaudo.add(laudoResponse);
			}

			SearchResponse searchResponse = new SearchResponse();
			searchResponse.setListPatologias(listPatologia);
			searchResponse.setListEdges(listEdge);
			searchResponse.setListLaudos(listLaudo);
			searchResponse.setListPacientes(listPaciente);

			return searchResponse;

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
}
