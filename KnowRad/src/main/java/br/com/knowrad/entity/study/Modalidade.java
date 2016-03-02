package br.com.knowrad.entity.study;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "modalidade", schema = "public")
public class Modalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idModalidade;
    private String modalidade;

    public Modalidade() {
    }

    public Modalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Modalidade(String modalidade, Long idModalidade) {
        this.modalidade = modalidade;
        this.idModalidade = idModalidade;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="modalidade_seq", sequenceName="modalidade_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modalidade_seq")
    @Column(name = "id_modalidade", unique = true, nullable = false)
    public Long getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Long idModalidade) {
        this.idModalidade = idModalidade;
    }

    @Column(name = "modalidade")
    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
