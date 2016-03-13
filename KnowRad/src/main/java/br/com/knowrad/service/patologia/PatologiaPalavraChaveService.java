package br.com.knowrad.service.patologia;

import br.com.knowrad.entity.patologia.PatologiaPalavraChave;

import java.util.List;

public interface PatologiaPalavraChaveService {
    void persist(PatologiaPalavraChave c);
    void remove(final Long id);
    PatologiaPalavraChave findByIds(Long idPatologia, Long idPalavraChave);
    List<PatologiaPalavraChave> findAllByIdPatologia(Long id);
}
