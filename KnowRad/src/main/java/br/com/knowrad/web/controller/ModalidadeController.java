package br.com.knowrad.web.controller;

import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.service.study.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/modalidade")
public class ModalidadeController {

    @Autowired
    private ModalidadeService modalidadeService;

    @RequestMapping(value = "/findAllDTO", method = { RequestMethod.GET })
    @ResponseBody
    public List<ModalidadeDTO> findAllDTO() {
        return modalidadeService.findAllDTO();
    }

}
