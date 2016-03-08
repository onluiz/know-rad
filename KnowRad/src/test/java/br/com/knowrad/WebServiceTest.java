package br.com.knowrad;

import br.com.knowrad.dto.LaudoDTO;
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

	@Test
	@Ignore
	public void queryTest() {
		
		String search = "TC DO PE";
		
		final String URL = "localhost";
		final String PORT = "8983";
		final String CONTEXT = "solr";
		final String CORE = "laudos";
		
		String urlString = "http://" + URL + ":" + PORT + "/" + CONTEXT + "/"+ CORE;
		
		SolrClient solr = new HttpSolrClient(urlString);
		
		SolrQuery query = new SolrQuery();
		query.setQuery("_text_:" + search);
		
		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		
		try {
			
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			String teste = "";
			System.out.println(teste);
			
			for(Map solrMap : list) {
				LaudoDTO dto = new LaudoDTO();
				dto.setIdPaciente(Util.verifyLong(solrMap.get("idPaciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nomePaciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				listLaudo.add(dto);
			}
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
