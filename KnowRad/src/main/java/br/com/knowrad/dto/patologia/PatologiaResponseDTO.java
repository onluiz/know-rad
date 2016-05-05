package br.com.knowrad.dto.patologia;

import java.io.Serializable;
import java.util.List;

public class PatologiaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private PatologiaDTO patologiaDTO;
    private List<TermoDTO> listTermoDTO;

    public PatologiaDTO getPatologiaDTO() {
        return patologiaDTO;
    }

    public void setPatologiaDTO(PatologiaDTO patologiaDTO) {
        this.patologiaDTO = patologiaDTO;
    }

    public List<TermoDTO> getListTermoDTO() {
        return listTermoDTO;
    }

    public void setListTermoDTO(List<TermoDTO> listTermoDTO) {
        this.listTermoDTO = listTermoDTO;
    }
}
