package br.com.knowrad.service.study;

import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.entity.study.Modalidade;

import java.util.List;

public interface ModalidadeService {
    List<Modalidade> findAll();
    List<ModalidadeDTO> findAllDTO();
}
