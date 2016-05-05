package br.com.knowrad.dto.patologia;

import br.com.knowrad.dto.Position;

import java.io.Serializable;

public class PatologiaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private PatologiaDTO data;

    private Position position;

    private Boolean selected;

    public PatologiaResponse() {
    }

    public PatologiaDTO getData() {
        return data;
    }

    public void setData(PatologiaDTO data) {
        this.data = data;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
