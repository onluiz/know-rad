package br.com.knowrad.dto;

import com.sun.javafx.geom.Edge;

import java.io.Serializable;

public class EdgeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private EdgeDTO data;

    private  Boolean selected;

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
