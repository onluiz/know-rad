package br.com.knowrad.entity.patologia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patologia_caso", schema = "public")
public class PatologiaCaso implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPatologiaCaso;
    private Patologia patologia;
    private Caso caso;

    public PatologiaCaso() {
    }

    public PatologiaCaso(Long idPatologiaCaso, Patologia patologia, Caso caso) {
        this.idPatologiaCaso = idPatologiaCaso;
        this.patologia = patologia;
        this.caso = caso;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="patologia_caso_seq", sequenceName="patologia_caso_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patologia_caso_seq")
    @Column(name = "id_patologia_caso", unique = true, nullable = false)
    public Long getIdPatologiaCaso() {
        return idPatologiaCaso;
    }

    public void setIdPatologiaCaso(Long idPatologiaCaso) {
        this.idPatologiaCaso = idPatologiaCaso;
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
    @JoinColumn(name = "id_caso")
    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }
}
