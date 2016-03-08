package br.com.knowrad.dto;

import java.io.Serializable;

public class CasoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCaso;
    private String titulo;
    private String laudo;

    public CasoDTO() {
    }

    public CasoDTO(Long idCaso, String titulo, String laudo) {
        this.idCaso = idCaso;
        this.titulo = titulo;
        this.laudo = laudo;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
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
}
