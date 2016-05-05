package br.com.knowrad.dto;

import br.com.knowrad.dto.patologia.PatologiaResponse;

import java.io.Serializable;
import java.util.List;

public class SearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PatologiaResponse> listPatologias;
    private List<LaudoResponse> listLaudos; //cada item desse representa um NODE de laudo
    private List<PacienteResponse> listPacientes;
    private List<EdgeResponse> listEdges;

    public List<PatologiaResponse> getListPatologias() {
        return listPatologias;
    }

    public void setListPatologias(List<PatologiaResponse> listPatologias) {
        this.listPatologias = listPatologias;
    }

    public List<LaudoResponse> getListLaudos() {
        return listLaudos;
    }

    public void setListLaudos(List<LaudoResponse> listLaudos) {
        this.listLaudos = listLaudos;
    }

    public List<EdgeResponse> getListEdges() {
        return listEdges;
    }

    public void setListEdges(List<EdgeResponse> listEdges) {
        this.listEdges = listEdges;
    }

    public List<PacienteResponse> getListPacientes() {
        return listPacientes;
    }

    public void setListPacientes(List<PacienteResponse> listPacientes) {
        this.listPacientes = listPacientes;
    }
}
