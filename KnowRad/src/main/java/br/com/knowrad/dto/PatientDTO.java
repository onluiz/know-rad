package br.com.knowrad.dto;

import java.io.Serializable;
import java.util.List;

public class PatientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String patId;

    //atributos utilizados pela tela
    private Boolean selected;
    private String[] cytoscape_alias_list;
    private String canonicalName;
    private String SUID;
    private String NodeType;
    private String name;
    private String shared_name;
    private String NodeTypeFormatted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String[] getCytoscape_alias_list() {
        return cytoscape_alias_list;
    }

    public void setCytoscape_alias_list(String[] cytoscape_alias_list) {
        this.cytoscape_alias_list = cytoscape_alias_list;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getSUID() {
        return SUID;
    }

    public void setSUID(String SUID) {
        this.SUID = SUID;
    }

    public String getNodeType() {
        return NodeType;
    }

    public void setNodeType(String nodeType) {
        NodeType = nodeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShared_name() {
        return shared_name;
    }

    public void setShared_name(String shared_name) {
        this.shared_name = shared_name;
    }

    public String getNodeTypeFormatted() {
        return NodeTypeFormatted;
    }

    public void setNodeTypeFormatted(String nodeTypeFormatted) {
        NodeTypeFormatted = nodeTypeFormatted;
    }
}
