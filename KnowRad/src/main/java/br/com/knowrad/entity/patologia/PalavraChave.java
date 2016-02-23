package br.com.knowrad.entity.patologia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "palavra_chave", schema = "public")
public class PalavraChave implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPalavraChave;
    private String palavra;

    public PalavraChave() {
    }

    public PalavraChave(Long idPalavraChave, String palavra) {
        this.idPalavraChave = idPalavraChave;
        this.palavra = palavra;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="palavra_chave_seq", sequenceName="palavra_chave_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "palavra_chave_seq")
    @Column(name = "id_palavra_chave", unique = true, nullable = false)
    public Long getIdPalavraChave() {
        return idPalavraChave;
    }

    public void setIdPalavraChave(Long idPalavraChave) {
        this.idPalavraChave = idPalavraChave;
    }

    @Column(name = "palavra")
    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
}
