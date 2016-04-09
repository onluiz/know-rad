package br.com.knowrad.dao.doenca;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.entity.doenca.Termo;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TermoDAOImpl extends AbstractDAO<Termo> implements TermoDAO {

    public List<Termo> findListByIdDoenca(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Termo> criteriaQuery = criteriaBuilder.createQuery(Termo.class);
        final Root<Termo> root = criteriaQuery.from(Termo.class);
//        root.fetch("doenca");
        criteriaQuery.where(criteriaBuilder.equal(root.get("doenca").get("id"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
