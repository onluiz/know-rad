package br.com.knowrad.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrConnection {

	final String URL = "localhost";
	final String PORT = "8983";
	final String CONTEXT = "solr";
	final String CORE = "laudos";
	final String URL_FINAL = "http://" + URL + ":" + PORT + "/" + CONTEXT + "/"+ CORE;
	
	public SolrClient getSolrConnection() {
		try {
			return new HttpSolrClient(URL_FINAL);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
