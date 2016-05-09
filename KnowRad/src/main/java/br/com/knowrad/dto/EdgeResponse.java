package br.com.knowrad.dto;

import java.io.Serializable;

public class EdgeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private EdgeDTO data;

    private  Boolean selected;

    public EdgeResponse() {
    }

    public EdgeResponse(EdgeDTO data, Boolean selected) {
        this.data = data;
        this.selected = selected;
    }

    public EdgeDTO getData() {
        return data;
    }

    public void setData(EdgeDTO data) {
        this.data = data;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
