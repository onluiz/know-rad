package br.com.knowrad.dto;

import java.io.Serializable;

public class PatientResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private PatientDTO data;

    private Position position;

    private Boolean selected;

    public PatientDTO getData() {
        return data;
    }

    public void setData(PatientDTO data) {
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
