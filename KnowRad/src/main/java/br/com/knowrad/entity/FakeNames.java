package br.com.knowrad.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fake_names", schema = "public")
public class FakeNames implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Boolean used;

    @Id
    @SequenceGenerator(allocationSize=1, name="fake_names_id_seq", sequenceName="fake_names_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fake_names_id_seq")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome")
    @Index(name="index_fake_names_nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "used", columnDefinition = "boolean default false")
    @Index(name="index_fake_names_used")
    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
