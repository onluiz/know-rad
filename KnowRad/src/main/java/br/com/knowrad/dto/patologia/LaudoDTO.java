package br.com.knowrad.dto.patologia;

import java.io.Serializable;
import java.util.List;

public class LaudoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idIndex;
	private String titulo;
	private String texto;
	private String textoLimpo;
	private String nomePaciente;
	private String modalidade;
	private Long idPaciente;
	private List<Long> doencas;

	public String getIdIndex() {
		return idIndex;
	}

	public void setIdIndex(String idIndex) {
		this.idIndex = idIndex;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTextoLimpo() {
		return textoLimpo;
	}

	public void setTextoLimpo(String textoLimpo) {
		this.textoLimpo = textoLimpo;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public List<Long> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<Long> doencas) {
		this.doencas = doencas;
	}
}
