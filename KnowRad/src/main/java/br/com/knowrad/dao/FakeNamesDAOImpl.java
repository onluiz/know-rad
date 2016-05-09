package br.com.knowrad.dao;

import br.com.knowrad.entity.FakeNames;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FakeNamesDAOImpl extends AbstractDAO<FakeNames> implements FakeNamesDAO {

    public FakeNames findOneNotUsed() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<FakeNames> criteriaQuery = criteriaBuilder.createQuery(FakeNames.class);
        final Root<FakeNames> root = criteriaQuery.from(FakeNames.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("used"), Boolean.FALSE));

        List<FakeNames> list = entityManager.createQuery(criteriaQuery).setMaxResults(1).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

}
