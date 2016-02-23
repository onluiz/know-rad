package br.com.knowrad.entity.patologia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patologia", schema = "public")
public class Patologia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPatologia;
    private String descricao;

    public Patologia() {
    }

    public Patologia(Long idPatologia, String descricao) {
        this.idPatologia = idPatologia;
        this.descricao = descricao;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="patologia_seq", sequenceName="patologia_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patologia_seq")
    @Column(name = "id_patologia", unique = true, nullable = false)
    public Long getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Long idPatologia) {
        this.idPatologia = idPatologia;
    }

    @Column(name = "descricao")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
