package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.entity.study.Modalidade;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CasoModalidadeDAOImpl extends AbstractDAO<CasoModalidade> implements CasoModalidadeDAO {

    public CasoModalidade findByIds(Long idCaso, Long idModalidade) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<CasoModalidade> criteriaQuery = criteriaBuilder.createQuery(CasoModalidade.class);

        final Root<CasoModalidade> root = criteriaQuery.from(CasoModalidade.class);
        Join<CasoModalidade, Caso> caso = root.join("caso");
        Join<CasoModalidade, Modalidade> modalidade = root.join("modalidade");

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(caso.get("idCaso"), idCaso));
        predicates.add(criteriaBuilder.equal(modalidade.get("idModalidade"), idModalidade));
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));

        List<CasoModalidade> list = entityManager.createQuery(criteriaQuery).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

    public List<CasoModalidade> findAllByIdCaso(Long idCaso) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<CasoModalidade> criteriaQuery = criteriaBuilder.createQuery(CasoModalidade.class);
        final Root<CasoModalidade> root = criteriaQuery.from(CasoModalidade.class);

        root.fetch("caso");
        root.fetch("modalidade");

        criteriaQuery.where(criteriaBuilder.equal(root.get("caso").get("idCaso"), idCaso));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
