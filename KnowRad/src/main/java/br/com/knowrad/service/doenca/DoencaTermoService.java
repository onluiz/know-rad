package br.com.knowrad.service.doenca;

import br.com.knowrad.entity.doenca.DoencaTermo;

import java.util.List;

public interface DoencaTermoService {
    void remove(final Long id);
    List<DoencaTermo> finListByIdDoenca(final Long id);
}
