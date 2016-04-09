package br.com.knowrad.dto;

import br.com.knowrad.dto.doenca.DoencaDTO;

import java.io.Serializable;

public class DoencaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private DoencaDTO data;

    private Position position;

    private Boolean selected;

    public DoencaResponse() {
    }

    public DoencaDTO getData() {
        return data;
    }

    public void setData(DoencaDTO data) {
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
