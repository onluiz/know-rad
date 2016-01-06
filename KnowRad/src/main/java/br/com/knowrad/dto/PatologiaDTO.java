package br.com.knowrad.dto;

public class PatologiaDTO {

    private String descricao;

    public PatologiaDTO() {
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
