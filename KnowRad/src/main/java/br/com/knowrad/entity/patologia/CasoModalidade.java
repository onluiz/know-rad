package br.com.knowrad.entity.patologia;

import br.com.knowrad.entity.study.Modalidade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "caso_modalidade", schema = "public")
public class CasoModalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCasoModalidade;
    private Caso caso;
    private Modalidade modalidade;

    public CasoModalidade() {
    }

    public CasoModalidade(Long idCasoModalidade, Caso caso, Modalidade modalidade) {
        this.idCasoModalidade = idCasoModalidade;
        this.caso = caso;
        this.modalidade = modalidade;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="caso_modalidade_seq", sequenceName="caso_modalidade_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caso_modalidade_seq")
    @Column(name = "id_caso_modalidade", unique = true, nullable = false)
    public Long getIdCasoModalidade() {
        return idCasoModalidade;
    }

    public void setIdCasoModalidade(Long idCasoModalidade) {
        this.idCasoModalidade = idCasoModalidade;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caso")
    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidade")
    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }
}
