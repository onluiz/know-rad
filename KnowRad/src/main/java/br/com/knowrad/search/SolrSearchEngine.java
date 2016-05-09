package br.com.knowrad.search;

import br.com.knowrad.dto.*;
import br.com.knowrad.dto.patologia.PatologiaResponse;
import br.com.knowrad.service.patologia.PatologiaService;
import br.com.knowrad.util.SolrConnection;
import br.com.knowrad.util.Util;
import br.com.knowrad.util.UtilGraph;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
	private UtilGraph utilGraph;

	private static SolrClient solr;

	@PostConstruct
	public void init() {
		solr = new SolrConnection().getSolrConnection();
	}

	public String searchLaudoContentById(String idSearch) {
		String texto = "";
		LaudoDTO laudoDTO = searchLaudoDTOById(idSearch);
		if(laudoDTO != null)
			texto = laudoDTO.getTexto();
		return texto;
	}

	public LaudoDTO searchLaudoDTOById(String idSearch) {
		LaudoDTO dto = null;
		try {
			SolrQuery query = new SolrQuery();
			query.setRows(1);
			query.setQuery("id:" + idSearch);
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();

			for(Map solrMap : list) {
				dto = new LaudoDTO();
				dto.setId(Util.verifyString(solrMap.get("id")));
				dto.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				dto.setPatologias(Util.objectToArrayListLong(solrMap.get("patologias")));
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDocumentList searchInIndex(String search) {
		SolrDocumentList result = null;
		try {
			SolrQuery query = new SolrQuery();
			query.setRows(1000);
			if(search == null || search.equals(""))
				query.setQuery("*:*");
			else {
				StringBuilder stringQuery = new StringBuilder();
				stringQuery.append("texto_limpo:*" + Util.cleanText(search) + "*");
				stringQuery.append(" OR ");
				stringQuery.append("pat_id:" + Util.verifyLong(search));
				stringQuery.append(" sort = data_exame desc ");
				query.setQuery(stringQuery.toString());
			}
			QueryResponse response = solr.query(query);
			result = response.getResults();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<LaudoDTO> searchLaudos(String search) {
		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		SolrDocumentList list = searchInIndex(search);
		for(Map solrMap : list) {
			LaudoDTO laudoDTO = new LaudoDTO();
			laudoDTO.setId(Util.verifyString(solrMap.get("id")));
			laudoDTO.setIdPaciente(Util.verifyLong(solrMap.get("id_paciente")));
			laudoDTO.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
			laudoDTO.setIdadePaciente(Util.verifyInteger(solrMap.get("idade_paciente")));
			laudoDTO.setDataExame(Util.verifyString(solrMap.get("data_exame_format")));
			laudoDTO.setTitulo(Util.verifyString(solrMap.get("titulo")));
			laudoDTO.setTexto(Util.verifyString(solrMap.get("texto")));
			laudoDTO.setModalidade(Util.verifyString(solrMap.get("modalidade")));
			laudoDTO.setPatologias(Util.objectToArrayListLong(solrMap.get("patologias")));
			laudoDTO.setListPatologiasDTO(patologiaService.findByIds(laudoDTO.getPatologias()));
			listLaudo.add(laudoDTO);
		}
		return listLaudo;
	}

	public SearchResponse searchLaudosGraph(String search) {
		List<LaudoResponse> listLaudo = new ArrayList<LaudoResponse>();
		List<PatologiaResponse> listPatologia = utilGraph.getListPatologiaResponse();
		List<PacienteResponse> listPaciente = new ArrayList<PacienteResponse>();
		List<EdgeResponse> listEdge = new ArrayList<EdgeResponse>();
		SolrDocumentList list = searchInIndex(search);

		for(Map solrMap : list) {
			LaudoDTO laudoDTO = new LaudoDTO();
			laudoDTO.setId(Util.verifyString(solrMap.get("id")));
			laudoDTO.setPatId(Util.verifyString(solrMap.get("pat_id")));
			laudoDTO.setNomePaciente(Util.verifyString(solrMap.get("nome_paciente")));
			laudoDTO.setIdadePaciente(Util.verifyInteger(solrMap.get("idade_paciente")));
			laudoDTO.setDataExame(Util.verifyString(solrMap.get("data_exame_format")));
			laudoDTO.setTitulo(Util.verifyString(solrMap.get("titulo")));
			laudoDTO.setTexto(Util.verifyString(solrMap.get("texto")));
			laudoDTO.setTextoLimpo(Util.verifyString(solrMap.get("texto_limpo")));
			laudoDTO.setModalidade(Util.verifyString(solrMap.get("modalidade")));
			laudoDTO.setPatologias(Util.objectToArrayListLong(solrMap.get("patologias")));

			for(Long idPatologia : laudoDTO.getPatologias()) {
				listEdge.add(utilGraph.getEdgeResponse(laudoDTO.getId(), idPatologia));
			}
			PacienteResponse pacienteResponse = utilGraph.updateListPacienteResponse(listPaciente, laudoDTO.getPatId());
			listEdge.add(utilGraph.getEdgeResponse(laudoDTO.getId(), laudoDTO.getPatId()));

			LaudoResponse laudoResponse = utilGraph.getLaudoResponse(laudoDTO);
			laudoResponse.getPosition().setX(pacienteResponse.getPosition().getX());
			laudoResponse.getPosition().setY(pacienteResponse.getPosition().getY());
			listLaudo.add(laudoResponse);
		}

		SearchResponse searchResponse = new SearchResponse();
		searchResponse.setListPatologias(listPatologia);
		searchResponse.setListEdges(listEdge);
		searchResponse.setListLaudos(listLaudo);
		searchResponse.setListPacientes(listPaciente);
		return searchResponse;
	}
	
}
