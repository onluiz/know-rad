package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.entity.patologia.Caso;
import org.springframework.stereotype.Repository;

@Repository
public class CasoDAOImpl extends AbstractDAO<Caso> implements CasoDAO {
}
