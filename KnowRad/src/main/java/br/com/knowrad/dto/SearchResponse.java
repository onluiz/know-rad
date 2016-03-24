package br.com.knowrad.dto;

import br.com.knowrad.dto.patologia.LaudoDTO;

import java.io.Serializable;
import java.util.List;

public class SearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<DoencaDTO> listDoencas;
    private List<LaudoDTO> listLaudos;
    private List<EdgeDTO> listEdges;

    public List<DoencaDTO> getListDoencas() {
        return listDoencas;
    }

    public void setListDoencas(List<DoencaDTO> listDoencas) {
        this.listDoencas = listDoencas;
    }

    public List<LaudoDTO> getListLaudos() {
        return listLaudos;
    }

    public void setListLaudos(List<LaudoDTO> listLaudos) {
        this.listLaudos = listLaudos;
    }

    public List<EdgeDTO> getListEdges() {
        return listEdges;
    }

    public void setListEdges(List<EdgeDTO> listEdges) {
        this.listEdges = listEdges;
    }
}
