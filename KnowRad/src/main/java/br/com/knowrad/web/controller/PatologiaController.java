package br.com.knowrad.web.controller;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilter;
import br.com.knowrad.dto.patologia.PatologiaResponseDTO;
import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.Termo;
import br.com.knowrad.service.patologia.PatologiaService;
import br.com.knowrad.service.patologia.TermoService;
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

    @Autowired
    private TermoService termoService;

    @RequestMapping(value = "/")
    public ModelAndView rotear() {
        return new ModelAndView(PATOLOGIAS);
    }

    @RequestMapping(value = "/listAjax", method = { RequestMethod.GET })
    @ResponseBody
    public DatatableResponse<PatologiaDTO> listAjax (
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

        DatatableResponse<PatologiaDTO> datatableResponse = service.findListDatatableByFilter(datatableRequest, new PatologiaFilter());

        return datatableResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public void save(@RequestBody PatologiaDTO patologiaDTO){
        Patologia patologia = new Patologia();
        patologia.setNome(patologiaDTO.getNome());
        service.persist(patologia);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public void update(@RequestBody PatologiaDTO patologiaDTO){
        Patologia patologia = service.findById(patologiaDTO.getId());
        patologia.setNome(patologiaDTO.getNome());
        service.merge(patologia);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = { RequestMethod.GET })
    public void remove(@RequestParam Long id){
        service.removeFull(id);
    }

    @ResponseBody
    @RequestMapping(value = "/saveTermo", method = { RequestMethod.POST })
    public void saveTermo(@RequestBody TermoDTO termoDTO){
        Termo termo = new Termo();
        termo.setNomeTermo(termoDTO.getNomeTermo());
        termo.setPatologia(service.findById(termoDTO.getIdPatologia()));
        termoService.persist(termo);
    }

    @ResponseBody
    @RequestMapping(value = "/updateTermo", method = { RequestMethod.POST })
    public void updateTermo(@RequestBody TermoDTO termoDTO){
        Termo termo = termoService.findById(termoDTO.getId());
        termo.setNomeTermo(termoDTO.getNomeTermo());
        termoService.merge(termo);
    }

    @ResponseBody
    @RequestMapping(value = "/removeTermo", method = { RequestMethod.GET })
    public void removeTermo(@RequestParam Long id){
        termoService.remove(id);
    }

    @ResponseBody
    @RequestMapping(value = "/findPatologiaResponseById", method = { RequestMethod.GET })
    public PatologiaResponseDTO findPatologiaResponseById(@RequestParam Long id){
        Patologia patologia = service.findById(id);
        PatologiaDTO dto = new PatologiaDTO();
        dto.setId(patologia.getId());
        dto.setNome(patologia.getNome());

        PatologiaResponseDTO response = new PatologiaResponseDTO();
        response.setPatologiaDTO(dto);
        response.setListTermoDTO(termoService.findListDTOByIdPatologia(id));

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/findListDTOByIdPatologia", method = { RequestMethod.GET })
    public List<TermoDTO> findListDTOByIdPatologia(@RequestParam Long id){
        return termoService.findListDTOByIdPatologia(id);
    }


}
