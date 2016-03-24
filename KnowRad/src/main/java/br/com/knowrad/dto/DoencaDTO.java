package br.com.knowrad.dto;

import java.io.Serializable;

public class DoencaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String nome;
    private String[] palavras;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getPalavras() {
        return palavras;
    }

    public void setPalavras(String[] palavras) {
        this.palavras = palavras;
    }
}
