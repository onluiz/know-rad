package br.com.knowrad.entity.patologia;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "termo", schema = "public")
public class Termo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeTermo;

    private Patologia patologia;

    @Id
    @SequenceGenerator(allocationSize=1, name="termo_seq", sequenceName="termo_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "termo_seq")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome_termo")
    @Index(name="index_nome_termo")
    public String getNomeTermo() {
        return nomeTermo;
    }

    public void setNomeTermo(String nomeTermo) {
        this.nomeTermo = nomeTermo;
    }

    @Index(name="indx_id_patologia_termo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patologia")
    public Patologia getPatologia() {
        return patologia;
    }

    public void setPatologia(Patologia patologia) {
        this.patologia = patologia;
    }
}
