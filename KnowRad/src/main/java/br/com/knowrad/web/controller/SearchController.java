package br.com.knowrad.web.controller;

import br.com.knowrad.dto.SearchResponse;
import br.com.knowrad.search.SolrSearchEngine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/searchLaudos")
public class SearchController {

	private SolrSearchEngine solrSearch = new SolrSearchEngine();
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
    @ResponseBody
    public SearchResponse search (@RequestParam String search) {
        return solrSearch.searchLaudos2(search);
    }
	
}
