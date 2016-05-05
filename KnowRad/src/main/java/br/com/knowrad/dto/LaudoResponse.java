package br.com.knowrad.dto;

import java.io.Serializable;

public class LaudoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private LaudoDTO data;

    private Position position;

    private Boolean selected;

    public LaudoDTO getData() {
        return data;
    }

    public void setData(LaudoDTO data) {
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
