package br.com.knowrad.dao.patologia;

import br.com.knowrad.entity.patologia.PalavraChave;

public interface PalavraChaveDAO {
    void persist(PalavraChave c);
    void merge(PalavraChave c);
    void remove(final Long id);
    PalavraChave findById(final Long id);
}
