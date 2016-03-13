package br.com.knowrad.web.controller.cadastros;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.CasoDTO;
import br.com.knowrad.dto.patologia.CasoFilterDTO;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import br.com.knowrad.entity.study.Modalidade;
import br.com.knowrad.service.patologia.CasoModalidadeService;
import br.com.knowrad.service.patologia.CasoService;
import br.com.knowrad.service.patologia.PatologiaCasoService;
import br.com.knowrad.service.patologia.PatologiaService;
import br.com.knowrad.service.study.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private PatologiaService patologiaService;

    @Autowired
    private CasoModalidadeService casoModalidadeService;

    @Autowired
    private PatologiaCasoService patologiaCasoService;

    @RequestMapping(value = "/listCasoAjax", method = { RequestMethod.GET })
    @ResponseBody
    public DatatableResponse<CasoDTO> listCasoAjax (
            HttpServletRequest request,
            HttpServletResponse response,
            // datatable
            @RequestParam Integer iDisplayStart,
            @RequestParam Integer iDisplayLength,
            @RequestParam Integer sColumns,
            @RequestParam Integer iColumns,
            @RequestParam String sSearch,
            @RequestParam Boolean bRegex,
            @RequestParam Integer iSortingCols,
            @RequestParam Integer iSortCol_0,
            @RequestParam String sSortDir_0,
            @RequestParam Integer sEcho
    ) {

        //data table resquest - dados da table
        DatatableRequest datatableRequest = new DatatableRequest(sEcho, iDisplayLength, iDisplayStart, sColumns, iColumns, sSearch, bRegex, iSortingCols, iSortCol_0, sSortDir_0);

        CasoFilterDTO filter = new CasoFilterDTO();

        DatatableResponse<CasoDTO> datatableResponse = casoService.findListDatatableByFilter(datatableRequest, filter);

        return datatableResponse;
    }

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
        Caso caso = new Caso();
        caso.setTitulo(casoDTO.getTitulo());
        caso.setLaudo(casoDTO.getLaudo());
        casoService.persist(caso);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public void update(@RequestBody CasoDTO casoDTO){
        if(casoDTO.getIdCaso() == null || casoDTO.getIdCaso() == 0)
            return;

        Caso caso = casoService.findById(casoDTO.getIdCaso());
        caso.setTitulo(casoDTO.getTitulo());
        caso.setLaudo(casoDTO.getLaudo());
        casoService.merge(caso);
    }

    @ResponseBody
    @RequestMapping(value = "/findDTOById", method = { RequestMethod.GET })
    public CasoDTO findDTOById(@RequestParam Long idCaso){
        return casoService.findDTOById(idCaso);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = { RequestMethod.GET })
    public void remove(@RequestParam Long idCaso){
        casoService.removeFull(idCaso);
    }

    @ResponseBody
    @RequestMapping(value = "/removeCasoModalidade", method = { RequestMethod.GET })
    public void removeCasoModalidade(@RequestParam Long idCaso, @RequestParam Long idModalidade){

        CasoModalidade casoModalidade = casoModalidadeService.findByIds(idCaso, idModalidade);
        if(casoModalidade == null)
            return;

        casoModalidadeService.remove(casoModalidade.getIdCasoModalidade());
    }

    @ResponseBody
    @RequestMapping(value = "/findAllModalidadesDTOByidCaso", method = { RequestMethod.GET })
    public List<ModalidadeDTO> findAllModalidadesDTOByidCaso(@RequestParam Long idCaso){
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

    @ResponseBody
    @RequestMapping(value = "/findAllPatologiasDTOByidCaso", method = { RequestMethod.GET })
    public List<PatologiaDTO> findAllPatologiasDTOByidCaso(@RequestParam Long idCaso){
        List<PatologiaCaso> listPatologiaCaso = patologiaCasoService.findAllByIdCaso(idCaso);
        List<PatologiaDTO> listPatologiaDTO = new ArrayList<PatologiaDTO>();
        for(PatologiaCaso patologiaCaso : listPatologiaCaso) {
            PatologiaDTO patologiaDTO = new PatologiaDTO();
            patologiaDTO.setIdPatologia(patologiaCaso.getPatologia().getIdPatologia());
            patologiaDTO.setDescricao(patologiaCaso.getPatologia().getDescricao());
            listPatologiaDTO.add(patologiaDTO);
        }
        return listPatologiaDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/saveCasoModalidade", method = { RequestMethod.GET })
    public void saveCasoModalidade(@RequestParam Long idCaso, @RequestParam Long idModalidade) {

        CasoModalidade casoModalidade = casoModalidadeService.findByIds(idCaso, idModalidade);
        if(casoModalidade != null && casoModalidade.getIdCasoModalidade() > 0)
            return;

        Modalidade modalidade = modalidadeService.findById(idModalidade);
        Caso caso = casoService.findById(idCaso);

        casoModalidade = new CasoModalidade();
        casoModalidade.setModalidade(modalidade);
        casoModalidade.setCaso(caso);

        casoModalidadeService.persist(casoModalidade);
    }

    @ResponseBody
    @RequestMapping(value = "/savePatologiaCaso", method = { RequestMethod.GET })
    public void savePatologiaCaso(@RequestParam Long idCaso, @RequestParam Long idPatologia) {

        PatologiaCaso patologiaCaso = patologiaCasoService.findByIds(idCaso, idPatologia);
        if(patologiaCaso != null && patologiaCaso.getIdPatologiaCaso() > 0)
            return;

        Patologia patologia = patologiaService.findById(idPatologia);
        Caso caso = casoService.findById(idCaso);

        patologiaCaso = new PatologiaCaso();
        patologiaCaso.setPatologia(patologia);
        patologiaCaso.setCaso(caso);

        patologiaCasoService.persist(patologiaCaso);
    }

}
