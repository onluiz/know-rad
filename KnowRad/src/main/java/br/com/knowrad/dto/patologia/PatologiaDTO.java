package br.com.knowrad.dto.patologia;

import java.io.Serializable;

public class PatologiaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPatologia;
    private String descricao;

    public PatologiaDTO() {
    }

    public PatologiaDTO(Long idPatologia, String descricao) {
        this.idPatologia = idPatologia;
        this.descricao = descricao;
    }

    public Long getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Long idPatologia) {
        this.idPatologia = idPatologia;
    }

    public PatologiaDTO(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
