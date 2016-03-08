package br.com.knowrad.web.controller.cadastros;

import br.com.knowrad.dto.CasoDTO;
import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.service.patologia.CasoModalidadeService;
import br.com.knowrad.service.patologia.CasoService;
import br.com.knowrad.service.study.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/caso")
public class CasoController {

    private static final String CASOS = "config/casos";

    @Autowired
    private ModalidadeService modalidadeService;

    @Autowired
    private CasoService casoService;

    @Autowired
    private CasoModalidadeService casoModalidadeService;

    @RequestMapping(value = "/")
    public ModelAndView roadmap() {
        ModelAndView modelAndView = new ModelAndView(CASOS);
        modelAndView.addObject("listModalidadesDTO", modalidadeService.findAllDTO());
        modelAndView.addObject("listCasosDTO", casoService.findAllDTO());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public void save(@RequestBody CasoDTO casoDTO){
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = { RequestMethod.GET })
    public String remove(@RequestParam Long idCaso){
        /**
         * TODO
         * remover associações com palavras-chave
         * remover associações com patologias
         * remover associações com modalidade
         */

        return "hehehehe " + idCaso;
    }

    @ResponseBody
    @RequestMapping(value = "/findAllByidCaso", method = { RequestMethod.GET })
    public List<ModalidadeDTO> findAllByidCaso(@RequestParam Long idCaso){
        List<CasoModalidade> listCasoModalidade = casoModalidadeService.findAllByIdCaso(idCaso);
        List<ModalidadeDTO> listModalidadeDTO = new ArrayList<ModalidadeDTO>();
        for(CasoModalidade casoModalidade : listCasoModalidade) {
            ModalidadeDTO dto = new ModalidadeDTO();
            dto.setIdModalidade(casoModalidade.getModalidade().getIdModalidade());
            dto.setModalidade(casoModalidade.getModalidade().getModalidade());
            listModalidadeDTO.add(dto);
        }
        return listModalidadeDTO;
    }

}
