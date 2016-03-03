package br.com.knowrad.web.controller.cadastros;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/caso")
public class CasoController {

    private static final String CASOS = "config/casos";

    @RequestMapping(value = "/")
    public ModelAndView roadmap() {
        return new ModelAndView(CASOS);
    }

}
