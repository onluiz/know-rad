package br.com.knowrad.dao.doenca;

import br.com.knowrad.dto.doenca.TermoDTO;
import br.com.knowrad.entity.doenca.Termo;

import java.util.List;

public interface TermoDAO {
    void persist(Termo termo);
    void merge(Termo termo);
    void remove(final Long id);
    Termo findById(final Long id);
    List<Termo> findListByIdDoenca(Long id);
    List<TermoDTO> findAllDTO();
}
