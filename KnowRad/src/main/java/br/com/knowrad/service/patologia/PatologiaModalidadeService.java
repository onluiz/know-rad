package br.com.knowrad.service.patologia;

import br.com.knowrad.entity.patologia.PatologiaModalidade;

import java.util.List;

public interface PatologiaModalidadeService {
    void persist(PatologiaModalidade c);
    void remove(final Long id);
    PatologiaModalidade findByIds(Long idPatologia, Long idModalidade);
    List<PatologiaModalidade> findAllByIdPatologia(Long id);
}
