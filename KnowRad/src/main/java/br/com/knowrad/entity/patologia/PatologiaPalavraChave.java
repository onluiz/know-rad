package br.com.knowrad.entity.patologia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patologia_palavra_chave", schema = "public")
public class PatologiaPalavraChave implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPatologiaPalavraChave;
    private Patologia patologia;
    private PalavraChave palavraChave;

    public PatologiaPalavraChave() {
    }

    public PatologiaPalavraChave(Long idPatologiaPalavraChave, Patologia patologia, PalavraChave palavraChave) {
        this.idPatologiaPalavraChave = idPatologiaPalavraChave;
        this.patologia = patologia;
        this.palavraChave = palavraChave;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="patologia_palavra_chave_seq", sequenceName="patologia_palavra_chave_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patologia_palavra_chave_seq")
    @Column(name = "id_patologia_palavra_chave", unique = true, nullable = false)
    public Long getIdPatologiaPalavraChave() {
        return idPatologiaPalavraChave;
    }

    public void setIdPatologiaPalavraChave(Long idPatologiaPalavraChave) {
        this.idPatologiaPalavraChave = idPatologiaPalavraChave;
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
    @JoinColumn(name = "id_palavra_chave")
    public PalavraChave getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(PalavraChave palavraChave) {
        this.palavraChave = palavraChave;
    }
}
