package br.com.knowrad.entity.patologia;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patologia", schema = "public")
public class Patologia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Set<Termo> termos = new HashSet<Termo>(0);

    @Id
    @SequenceGenerator(allocationSize=1, name="patologia_seq", sequenceName="patologia_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patologia_seq")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome")
    @Index(name="index_nome_patologia")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patologia")
    public Set<Termo> getTermos() {
        return termos;
    }

    public void setTermos(Set<Termo> termos) {
        this.termos = termos;
    }
}
