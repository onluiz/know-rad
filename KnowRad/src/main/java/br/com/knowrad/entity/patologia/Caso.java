package br.com.knowrad.entity.patologia;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "caso", schema = "public")
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCaso;
    private String titulo;
    private String laudo;

    public Caso() {
    }

    public Caso(Long idCaso, String titulo, String laudo) {
        this.idCaso = idCaso;
        this.titulo = titulo;
        this.laudo = laudo;
    }

    @Id
    @SequenceGenerator(allocationSize=1, name="caso_seq", sequenceName="caso_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caso_seq")
    @Column(name = "id_caso", unique = true, nullable = false)
    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    @Column(name = "titulo")
    @Index(name="index_caso_titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "laudo")
    @Index(name="index_caso_laudo")
    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }
}
