package br.com.knowrad.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "laudo", schema = "public")
public class Laudo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String texto;
    private String textoLimpo;
    private String modalidade;
    private Date studyDate;
    private String accessionNo;

    private Paciente paciente;

    @Id
    @SequenceGenerator(allocationSize=1, name="laudo_seq", sequenceName="laudo_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laudo_seq")
    @Column(name = "id_laudo", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "texto")
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Column(name = "texto_limpo")
    public String getTextoLimpo() {
        return textoLimpo;
    }

    public void setTextoLimpo(String textoLimpo) {
        this.textoLimpo = textoLimpo;
    }

    @Column(name = "modalidade")
    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    @Column(name = "study_date")
    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    @Column(name = "accession_no")
    public String getAccessionNo() {
        return accessionNo;
    }

    public void setAccessionNo(String accessionNo) {
        this.accessionNo = accessionNo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
