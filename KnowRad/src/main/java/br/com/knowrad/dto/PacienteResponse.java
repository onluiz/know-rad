package br.com.knowrad.dto;

import java.io.Serializable;

public class PacienteResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private PacienteDTO data;
    private Position position;
    private Boolean selected;

    public PacienteDTO getData() {
        return data;
    }

    public void setData(PacienteDTO data) {
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
