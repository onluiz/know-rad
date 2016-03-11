package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatologiaCasoDAOImpl extends AbstractDAO<PatologiaCaso> implements PatologiaCasoDAO {

    public PatologiaCaso findByIds(Long idCaso, Long idPatologia) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PatologiaCaso> criteriaQuery = criteriaBuilder.createQuery(PatologiaCaso.class);

        final Root<PatologiaCaso> root = criteriaQuery.from(PatologiaCaso.class);
        Join<PatologiaCaso, Caso> caso = root.join("caso");
        Join<PatologiaCaso, Patologia> patologia = root.join("patologia");

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(caso.get("idCaso"), idCaso));
        predicates.add(criteriaBuilder.equal(patologia.get("idPatologia"), idPatologia));
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));

        List<PatologiaCaso> list = entityManager.createQuery(criteriaQuery).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

    public List<PatologiaCaso> findAllByIdCaso(Long idCaso) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PatologiaCaso> criteriaQuery = criteriaBuilder.createQuery(PatologiaCaso.class);
        final Root<PatologiaCaso> root = criteriaQuery.from(PatologiaCaso.class);

        root.fetch("caso");
        root.fetch("patologia");

        criteriaQuery.where(criteriaBuilder.equal(root.get("caso").get("idCaso"), idCaso));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
