package br.com.knowrad.dao.patologia;

import br.com.knowrad.entity.patologia.PatologiaPalavraChave;

import java.util.List;

public interface PatologiaPalavraChaveDAO {
    void persist(PatologiaPalavraChave c);
    void remove(final Long id);
    PatologiaPalavraChave findByIds(Long idPatologia, Long idPalavraChave);
    List<PatologiaPalavraChave> findAllByIdPatologia(Long id);
}
