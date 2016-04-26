package br.com.knowrad.dto;

import java.io.Serializable;
import java.util.List;

public class SearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<DoencaResponse> listDoencas;
    private List<LaudoResponse> listLaudos; //cada item desse representa um NODE de laudo
    private List<PacienteResponse> listPacientes;
    private List<EdgeResponse> listEdges;

    public List<DoencaResponse> getListDoencas() {
        return listDoencas;
    }

    public void setListDoencas(List<DoencaResponse> listDoencas) {
        this.listDoencas = listDoencas;
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
