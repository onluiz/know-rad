package br.com.knowrad.dto;

import java.io.Serializable;

public class EdgeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id; //id do idge
    private String source; //LAUDO
    private String target; //DOENCA
    private Boolean selected;
    private String canonicalName;
    private String SUID;
    private String name;
    private String interaction;
    private String shared_interaction;
    private String shared_name;

    public EdgeDTO() {
        this.id = "edge-" + Math.random();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getShared_interaction() {
        return shared_interaction;
    }

    public void setShared_interaction(String shared_interaction) {
        this.shared_interaction = shared_interaction;
    }

    public String getShared_name() {
        return shared_name;
    }

    public void setShared_name(String shared_name) {
        this.shared_name = shared_name;
    }
}
