package br.com.knowrad.dto.patologia;

import java.io.Serializable;

public class TermoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeTermo;
    private Long idPatologia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTermo() {
        return nomeTermo;
    }

    public void setNomeTermo(String nomeTermo) {
        this.nomeTermo = nomeTermo;
    }

    public Long getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Long idPatologia) {
        this.idPatologia = idPatologia;
    }
}
