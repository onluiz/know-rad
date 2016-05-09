package br.com.knowrad.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paciente", schema = "public")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String patId;
    private String nome;
    private Date dataNascimento;
    private Set<Laudo> laudos = new HashSet<Laudo>(0);

    @Id
    @SequenceGenerator(allocationSize=1, name="paciente_seq", sequenceName="paciente_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_seq")
    @Column(name = "id_paciente", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "pat_id")
    @Index(name="index_pat_id")
    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    @Column(name = "nome")
    @Index(name="index_nome_paciente")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_nascimento", length = 29)
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")
    public Set<Laudo> getLaudos() {
        return laudos;
    }

    public void setLaudos(Set<Laudo> laudos) {
        this.laudos = laudos;
    }
}
