package br.com.knowrad.web.controller;

import br.com.knowrad.dto.SearchResponse;
import br.com.knowrad.dto.patologia.LaudoDTO;
import br.com.knowrad.search.SolrIndexerEngine;
import br.com.knowrad.search.SolrSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/searchLaudos")
public class SearchController {

    @Autowired
	private SolrSearchEngine solrSearch;

    @Autowired
    private SolrIndexerEngine solrIndexer;

	@RequestMapping(value = "/search", method = { RequestMethod.GET })
    @ResponseBody
    public SearchResponse search(@RequestParam String search) {
        return solrSearch.searchLaudos2(search);
    }

    @RequestMapping(value = "/normal-search", method = { RequestMethod.GET })
    @ResponseBody
    public List<LaudoDTO> normalSearch(@RequestParam String search) {
        return solrSearch.searchLaudos(search);
    }

    @RequestMapping(value = "/search-by-id", method = { RequestMethod.GET })
    @ResponseBody
    public String searchById(@RequestParam String id) {
        return solrSearch.searchLaudosById(id);
    }

    @RequestMapping(value = "/search-dto-by-id", method = { RequestMethod.GET })
    @ResponseBody
    public LaudoDTO searchLaudoDTOById(@RequestParam String id) {
        return solrSearch.searchLaudoDTOById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/indexar", method = { RequestMethod.GET })
    public void indexar() {
        solrIndexer.indexar();
    }
	
}
