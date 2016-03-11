package br.com.knowrad.web.controller;

import br.com.knowrad.dto.PatologiaDTO;
import br.com.knowrad.service.patologia.PatologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/patologia")
public class PatologiaController {

    @Autowired
    private PatologiaService service;

    @ResponseBody
    @RequestMapping(value = "/search", method = { RequestMethod.GET })
    public List<PatologiaDTO> search(@RequestParam String searchText, @RequestParam Integer limit) {
        return service.search(searchText, limit);
    }

}
