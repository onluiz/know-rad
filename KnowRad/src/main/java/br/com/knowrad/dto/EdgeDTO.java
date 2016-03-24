package br.com.knowrad.dto;

import java.io.Serializable;

public class EdgeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long idDoenca;
    private String idLaudoIndex;

    public EdgeDTO() {
    }

    public EdgeDTO(long idDoenca, String idLaudoIndex) {
        this.idDoenca = idDoenca;
        this.idLaudoIndex = idLaudoIndex;
    }

    public long getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(long idDoenca) {
        this.idDoenca = idDoenca;
    }

    public String getIdLaudoIndex() {
        return idLaudoIndex;
    }

    public void setIdLaudoIndex(String idLaudoIndex) {
        this.idLaudoIndex = idLaudoIndex;
    }
}
