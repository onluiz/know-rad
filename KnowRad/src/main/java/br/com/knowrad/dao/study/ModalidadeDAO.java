package br.com.knowrad.dao.study;

import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.entity.study.Modalidade;

import java.util.List;

public interface ModalidadeDAO {

    void persist(Modalidade m);

    List<Modalidade> findAll();

    Modalidade findById(Long id);

    List<ModalidadeDTO> search(String searchText, Integer limit);
}
