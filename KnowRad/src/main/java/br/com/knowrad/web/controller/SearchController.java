package br.com.knowrad.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.knowrad.dto.LaudoDTO;
import br.com.knowrad.search.SolrSearchEngine;

@Controller
@RequestMapping("/searchLaudos")
public class SearchController {

	private SolrSearchEngine solrSearch = new SolrSearchEngine();
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
    @ResponseBody
    public List<LaudoDTO> search (@RequestParam String search) {
        return solrSearch.searchLaudos(search);
    }
	
}
