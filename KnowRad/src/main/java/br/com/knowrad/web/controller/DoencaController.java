package br.com.knowrad.web.controller;

import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.dto.doenca.DoencaFilter;
import br.com.knowrad.dto.doenca.TermoDTO;
import br.com.knowrad.entity.doenca.Doenca;
import br.com.knowrad.entity.doenca.Termo;
import br.com.knowrad.service.doenca.DoencaService;
import br.com.knowrad.service.doenca.TermoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping("/doenca")
public class DoencaController {

    private static final String DOENCAS = "config/doencas";

    @Autowired
    private DoencaService service;

    @Autowired
    private TermoService termoService;

    @RequestMapping(value = "/")
    public ModelAndView rotear() {
        return new ModelAndView(DOENCAS);
    }

    @RequestMapping(value = "/listAjax", method = { RequestMethod.GET })
    @ResponseBody
    public DatatableResponse<DoencaDTO> listAjax (
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

        DatatableResponse<DoencaDTO> datatableResponse = service.findListDatatableByFilter(datatableRequest, new DoencaFilter());

        return datatableResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public void save(@RequestBody DoencaDTO doencaDTO){
        Doenca doenca = new Doenca();
        doenca.setNome(doencaDTO.getNome());
        doenca.setCytoscape_alias_list(doencaDTO.getNome());
        doenca.setName(doencaDTO.getNome());
        doenca.setCanonicalName(doencaDTO.getNome());
        doenca.setShared_name(doencaDTO.getNome());
        doenca.setNodeType("RedWine");
        doenca.setNodeTypeFormatted("RedWine");
        doenca.setSelected(Boolean.FALSE);
        service.persist(doenca);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public void update(@RequestBody DoencaDTO doencaDTO){
        Doenca doenca = service.findById(doencaDTO.getId());
        doenca.setNome(doencaDTO.getNome());
        doenca.setCytoscape_alias_list(doencaDTO.getNome());
        doenca.setName(doencaDTO.getNome());
        doenca.setCanonicalName(doencaDTO.getNome());
        doenca.setShared_name(doencaDTO.getNome());
        service.merge(doenca);
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
        termo.setDoenca(service.findById(termoDTO.getIdDoenca()));
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
    @RequestMapping(value = "/findDoencaResponseById", method = { RequestMethod.GET })
    public DoencaResponse findDoencaResponseById(@RequestParam Long id){
        Doenca doenca = service.findById(id);
        DoencaDTO dto = new DoencaDTO();
        dto.setId(doenca.getId());
        dto.setNome(doenca.getNome());

        DoencaResponse response = new DoencaResponse();
        response.setDoencaDTO(dto);
        response.setListTermoDTO(termoService.findListDTOByIdDoenca(id));

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/findListDTOByIdDoenca", method = { RequestMethod.GET })
    public List<TermoDTO> findListDTOByIdDoenca(@RequestParam Long id){
        return termoService.findListDTOByIdDoenca(id);
    }

    class DoencaResponse implements Serializable {

        private static final long serialVersionUID = 1L;

        private DoencaDTO doencaDTO;
        private List<TermoDTO> listTermoDTO;

        public DoencaDTO getDoencaDTO() {
            return doencaDTO;
        }

        public void setDoencaDTO(DoencaDTO doencaDTO) {
            this.doencaDTO = doencaDTO;
        }

        public List<TermoDTO> getListTermoDTO() {
            return listTermoDTO;
        }

        public void setListTermoDTO(List<TermoDTO> listTermoDTO) {
            this.listTermoDTO = listTermoDTO;
        }
    }

}
