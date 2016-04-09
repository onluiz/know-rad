package br.com.knowrad.dto.doenca;

import java.io.Serializable;

public class TermoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeTermo;
    private Long idDoenca;

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

    public Long getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(Long idDoenca) {
        this.idDoenca = idDoenca;
    }
}
