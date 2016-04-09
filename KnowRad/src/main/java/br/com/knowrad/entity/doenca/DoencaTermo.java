package br.com.knowrad.entity.doenca;

import java.io.Serializable;

public class DoencaTermo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Doenca doenca;
    private Termo termo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    public Termo getTermo() {
        return termo;
    }

    public void setTermo(Termo termo) {
        this.termo = termo;
    }
}
