package br.com.knowrad.service.patologia;

import br.com.knowrad.entity.patologia.PalavraChave;

public interface PalavraChaveService {
    void persist(PalavraChave c);
    void merge(PalavraChave c);
    void remove(final Long id);
    PalavraChave findById(final Long id);
}
