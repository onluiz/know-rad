package br.com.knowrad;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IndexingTest {

	@Test
	public void indexDataTest() {
		
		String urlString = "http://localhost:8983/solr/techproducts";
		SolrClient solr = new HttpSolrClient(urlString);

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "552199");
		document.addField("name", "Gouda cheese wheel");
		document.addField("price", "49.99");
		
		try {
			
//			UpdateResponse response = solr.add(document);
			solr.add(document);
			solr.commit();
			solr.close();
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void queryDataTest() {
		
		String urlString = "http://localhost:8983/solr/techproducts";
		SolrClient solr = new HttpSolrClient(urlString);
		
		SolrQuery query = new SolrQuery();
		query.setQuery("id:552199");
		
		try {
			
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			String teste = "";
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void readJsonFile() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("laudos.json");
		
		try {
			
			String content = IOUtils.toString(inputStream);
			
			JsonElement jelement = new JsonParser().parse(content);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("listLaudos");
			
			List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
			
			for(int i = 0; i < jarray.size(); i++) {
				
				JsonObject item = jarray.get(i).getAsJsonObject();

				LaudoDTO dto = new LaudoDTO();
				dto.setIdPaciente(item.get("idPaciente").getAsLong());
				dto.setNomePaciente(item.get("nomePaciente").getAsString());
				dto.setTitulo(item.get("titulo").getAsString());
				dto.setTexto(item.get("texto").getAsString());
				listLaudo.add(dto);

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	class LaudoDTO {
		
		private String titulo;
		private String texto;
		private String nomePaciente;
		private Long idPaciente;
		
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getTexto() {
			return texto;
		}
		public void setTexto(String texto) {
			this.texto = texto;
		}
		public String getNomePaciente() {
			return nomePaciente;
		}
		public void setNomePaciente(String nomePaciente) {
			this.nomePaciente = nomePaciente;
		}
		public Long getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(Long idPaciente) {
			this.idPaciente = idPaciente;
		}
		
	}
	
}
