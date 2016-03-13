package br.com.knowrad.dao.patologia;

import br.com.knowrad.entity.patologia.PatologiaModalidade;

import java.util.List;

public interface PatologiaModalidadeDAO {
    void persist(PatologiaModalidade c);
    void remove(final Long id);
    PatologiaModalidade findByIds(Long idPatologia, Long idModalidade);
    List<PatologiaModalidade> findAllByIdPatologia(Long id);
}
