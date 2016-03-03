package br.com.knowrad.dto.study;

import java.io.Serializable;

public class ModalidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idModalidade;
    private String modalidade;

    public ModalidadeDTO() {
    }

    public ModalidadeDTO(Long idModalidade, String modalidade) {
        this.idModalidade = idModalidade;
        this.modalidade = modalidade;
    }

    public Long getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Long idModalidade) {
        this.idModalidade = idModalidade;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
