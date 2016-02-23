package br.com.knowrad.entity.patologia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patologia_modalidade", schema = "public")
public class PatologiaModalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPatologiaModalidade;
    private Patologia patologia;
    private Modalidade modalidade;

    public PatologiaModalidade() {
    }

    public PatologiaModalidade(Long idPatologiaModalidade, Patologia patologia, Modalidade modalidade) {
        this.idPatologiaModalidade = idPatologiaModalidade;
        this.patologia = patologia;
        this.modalidade = modalidade;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="patologia_modalidade_seq", sequenceName="patologia_modalidade_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patologia_modalidade_seq")
    @Column(name = "id_patologia_modalidade", unique = true, nullable = false)
    public Long getIdPatologiaModalidade() {
        return idPatologiaModalidade;
    }

    public void setIdPatologiaModalidade(Long idPatologiaModalidade) {
        this.idPatologiaModalidade = idPatologiaModalidade;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patologia")
    public Patologia getPatologia() {
        return patologia;
    }

    public void setPatologia(Patologia patologia) {
        this.patologia = patologia;
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
