package br.com.knowrad.dto.patologia;

import java.util.List;

public class PatologiaCaseDTO {

    private List<PatologiaDTO> listPatologiaDTO;
    private String tituloLaudo;
    private String modalidadeLaudo;
    private String textoLaudo;

    public List<PatologiaDTO> getListPatologiaDTO() {
        return listPatologiaDTO;
    }

    public void setListPatologiaDTO(List<PatologiaDTO> listPatologiaDTO) {
        this.listPatologiaDTO = listPatologiaDTO;
    }

    public String getTituloLaudo() {
        return tituloLaudo;
    }

    public void setTituloLaudo(String tituloLaudo) {
        this.tituloLaudo = tituloLaudo;
    }

    public String getModalidadeLaudo() {
        return modalidadeLaudo;
    }

    public void setModalidadeLaudo(String modalidadeLaudo) {
        this.modalidadeLaudo = modalidadeLaudo;
    }

    public String getTextoLaudo() {
        return textoLaudo;
    }

    public void setTextoLaudo(String textoLaudo) {
        this.textoLaudo = textoLaudo;
    }
}
