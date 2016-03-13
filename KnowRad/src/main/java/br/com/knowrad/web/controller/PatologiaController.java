package br.com.knowrad.web.controller;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilterDTO;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.service.patologia.PatologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/patologia")
public class PatologiaController {

    private static final String PATOLOGIAS = "config/patologias";

    @Autowired
    private PatologiaService service;

    @RequestMapping(value = "/")
    public ModelAndView rotear() {
        return new ModelAndView(PATOLOGIAS);
    }

    @RequestMapping(value = "/listPatologiaAjax", method = { RequestMethod.GET })
    @ResponseBody
    public DatatableResponse<PatologiaDTO> listPatologiaAjax (
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

        PatologiaFilterDTO filter = new PatologiaFilterDTO();

        DatatableResponse<PatologiaDTO> datatableResponse = service.findListDatatableByFilter(datatableRequest, filter);

        return datatableResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public void save(@RequestBody PatologiaDTO patologiaDTO){
        Patologia patologia = new Patologia();
        patologia.setDescricao(patologiaDTO.getDescricao());
        service.persist(patologia);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public void update(@RequestBody PatologiaDTO patologiaDTO){
        if(patologiaDTO.getIdPatologia() == null || patologiaDTO.getIdPatologia() == 0)
            return;

        Patologia caso = service.findById(patologiaDTO.getIdPatologia());
        caso.setDescricao(patologiaDTO.getDescricao());
        service.merge(caso);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = { RequestMethod.GET })
    public void remove(@RequestParam Long idPatologia){
        service.removeFull(idPatologia);
    }

    @ResponseBody
    @RequestMapping(value = "/findDTOById", method = { RequestMethod.GET })
    public PatologiaDTO findDTOById(@RequestParam Long idPatologia){
        return service.findDTOById(idPatologia);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = { RequestMethod.GET })
    public List<PatologiaDTO> search(@RequestParam String searchText, @RequestParam Integer limit) {
        return service.search(searchText, limit);
    }

}
