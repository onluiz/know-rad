package br.com.knowrad.dto.doenca;

import java.io.Serializable;
import java.util.List;

public class DoencaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private DoencaDTO doencaDTO;
    private List<TermoDTO> listTermoDTO;

    public DoencaDTO getDoencaDTO() {
        return doencaDTO;
    }

    public void setDoencaDTO(DoencaDTO doencaDTO) {
        this.doencaDTO = doencaDTO;
    }

    public List<TermoDTO> getListTermoDTO() {
        return listTermoDTO;
    }

    public void setListTermoDTO(List<TermoDTO> listTermoDTO) {
        this.listTermoDTO = listTermoDTO;
    }
}
