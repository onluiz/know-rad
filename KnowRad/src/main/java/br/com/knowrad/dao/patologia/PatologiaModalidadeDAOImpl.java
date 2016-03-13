package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaModalidade;
import br.com.knowrad.entity.study.Modalidade;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatologiaModalidadeDAOImpl extends AbstractDAO<PatologiaModalidade> implements PatologiaModalidadeDAO {

    public PatologiaModalidade findByIds(Long idPatologia, Long idModalidade) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PatologiaModalidade> criteriaQuery = criteriaBuilder.createQuery(PatologiaModalidade.class);

        final Root<PatologiaModalidade> root = criteriaQuery.from(PatologiaModalidade.class);
        Join<PatologiaModalidade, Patologia> patologia = root.join("patologia");
        Join<PatologiaModalidade, Modalidade> modalidade = root.join("modalidade");

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(patologia.get("idPatologia"), idPatologia));
        predicates.add(criteriaBuilder.equal(modalidade.get("idModalidade"), idModalidade));
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));

        List<PatologiaModalidade> list = entityManager.createQuery(criteriaQuery).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

    public List<PatologiaModalidade> findAllByIdPatologia(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PatologiaModalidade> criteriaQuery = criteriaBuilder.createQuery(PatologiaModalidade.class);
        final Root<PatologiaModalidade> root = criteriaQuery.from(PatologiaModalidade.class);

        root.fetch("patologia");
        root.fetch("modalidade");

        criteriaQuery.where(criteriaBuilder.equal(root.get("patologia").get("idPatologia"), id));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
