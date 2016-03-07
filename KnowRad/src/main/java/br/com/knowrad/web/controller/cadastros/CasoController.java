package br.com.knowrad.web.controller.cadastros;

import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.entity.study.Modalidade;
import br.com.knowrad.service.patologia.CasoModalidadeService;
import br.com.knowrad.service.patologia.CasoService;
import br.com.knowrad.service.study.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
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
        return new ModelAndView(CASOS) {{
            addObject("listModalidadesDTO", modalidadeService.findAllDTO());
        }};
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public void save(@RequestBody CasoSaveResponseDTO casoSaveResponseDTO){
        Caso caso = new Caso();
        caso.setTitulo(casoSaveResponseDTO.getTitulo());
        caso.setLaudo(casoSaveResponseDTO.getLaudo());
        casoService.persist(caso);

        if(casoSaveResponseDTO.getListIdModalidades() != null && !casoSaveResponseDTO.getListIdModalidades().isEmpty()) {

            for(Long idModalidade : casoSaveResponseDTO.getListIdModalidades()) {
                Modalidade modalidade = modalidadeService.findById(idModalidade);

                CasoModalidade casoModalidade = new CasoModalidade();
                casoModalidade.setCaso(caso);
                casoModalidade.setModalidade(modalidade);
                casoModalidadeService.persist(casoModalidade);
            }

        }
    }

    public class CasoSaveResponseDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long idCasoEdicao;
        private String titulo;
        private String laudo;
        private List<Long> listIdModalidades;

        public CasoSaveResponseDTO() {
        }

        public CasoSaveResponseDTO(Long idCasoEdicao, String titulo, String laudo, List<Long> listIdModalidades) {
            this.idCasoEdicao = idCasoEdicao;
            this.titulo = titulo;
            this.laudo = laudo;
            this.listIdModalidades = listIdModalidades;
        }

        public Long getIdCasoEdicao() {
            return idCasoEdicao;
        }

        public void setIdCasoEdicao(Long idCasoEdicao) {
            this.idCasoEdicao = idCasoEdicao;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getLaudo() {
            return laudo;
        }

        public void setLaudo(String laudo) {
            this.laudo = laudo;
        }

        public List<Long> getListIdModalidades() {
            return listIdModalidades;
        }

        public void setListIdModalidades(List<Long> listIdModalidades) {
            this.listIdModalidades = listIdModalidades;
        }
    }

}
