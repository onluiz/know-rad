package br.com.knowrad.entity.doenca;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doenca", schema = "public")
public class Doenca implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Boolean selected;
    private String cytoscape_alias_list;
    private String canonicalName;
    private String SUID;
    private String NodeType;
    private String name;
    private String shared_name;
    private String NodeTypeFormatted;

    private Set<Termo> termos = new HashSet<Termo>(0);

    @Id
    @SequenceGenerator(allocationSize=1, name="doenca_seq", sequenceName="doenca_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doenca_seq")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome")
    @Index(name="index_nome_doenca")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "selected", columnDefinition = "boolean default false")
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Column(name = "cytoscape_alias_list")
    public String getCytoscape_alias_list() {
        return cytoscape_alias_list;
    }

    public void setCytoscape_alias_list(String cytoscape_alias_list) {
        this.cytoscape_alias_list = cytoscape_alias_list;
    }

    @Column(name = "canonical_name")
    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    @Column(name = "suid")
    public String getSUID() {
        return SUID;
    }

    public void setSUID(String SUID) {
        this.SUID = SUID;
    }

    @Column(name = "node_type")
    public String getNodeType() {
        return NodeType;
    }

    public void setNodeType(String nodeType) {
        NodeType = nodeType;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "shared_name")
    public String getShared_name() {
        return shared_name;
    }

    public void setShared_name(String shared_name) {
        this.shared_name = shared_name;
    }

    @Column(name = "node_type_formatted")
    public String getNodeTypeFormatted() {
        return NodeTypeFormatted;
    }

    public void setNodeTypeFormatted(String nodeTypeFormatted) {
        NodeTypeFormatted = nodeTypeFormatted;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doenca")
    public Set<Termo> getTermos() {
        return termos;
    }

    public void setTermos(Set<Termo> termos) {
        this.termos = termos;
    }
}
