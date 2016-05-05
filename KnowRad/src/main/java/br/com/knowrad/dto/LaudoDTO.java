package br.com.knowrad.dto;

import br.com.knowrad.dto.patologia.PatologiaDTO;

import java.io.Serializable;
import java.util.List;

public class LaudoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String titulo;
	private String texto;
	private String textoLimpo;
	private String nomePaciente;
	private String modalidade;
	private Long idPaciente;
	private List<Long> patologias;
    private List<PatologiaDTO> listPatologiasDTO;

    //atributos utilizados pela tela
    private Boolean selected;
    private String[] cytoscape_alias_list;
    private String canonicalName;
    private String SUID;
    private String NodeType;
    private String name;
    private String shared_name;
    private String NodeTypeFormatted;

    private String tipo = "LAUDO";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Long> getPatologias() {
        return patologias;
    }

    public void setPatologias(List<Long> patologias) {
        this.patologias = patologias;
    }

    public List<PatologiaDTO> getListPatologiasDTO() {
        return listPatologiasDTO;
    }

    public void setListPatologiasDTO(List<PatologiaDTO> listPatologiasDTO) {
        this.listPatologiasDTO = listPatologiasDTO;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String[] getCytoscape_alias_list() {
        return cytoscape_alias_list;
    }

    public void setCytoscape_alias_list(String[] cytoscape_alias_list) {
        this.cytoscape_alias_list = cytoscape_alias_list;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getSUID() {
        return SUID;
    }

    public void setSUID(String SUID) {
        this.SUID = SUID;
    }

    public String getNodeType() {
        return NodeType;
    }

    public void setNodeType(String nodeType) {
        NodeType = nodeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShared_name() {
        return shared_name;
    }

    public void setShared_name(String shared_name) {
        this.shared_name = shared_name;
    }

    public String getNodeTypeFormatted() {
        return NodeTypeFormatted;
    }

    public void setNodeTypeFormatted(String nodeTypeFormatted) {
        NodeTypeFormatted = nodeTypeFormatted;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
