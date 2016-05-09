package br.com.knowrad.dao;

import br.com.knowrad.entity.Laudo;
import br.com.knowrad.entity.Paciente;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LaudoDAOImpl extends AbstractDAO<Laudo> implements LaudoDAO {

    public List<Laudo> findAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Laudo> criteriaQuery = criteriaBuilder.createQuery(Laudo.class);
        final Root<Laudo> root = criteriaQuery.from(Laudo.class);
        Fetch<Laudo, Paciente> joinPaciente = root.fetch("paciente");
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Laudo findByAccessionNo(String accessionNo) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Laudo> criteriaQuery = criteriaBuilder.createQuery(Laudo.class);
        final Root<Laudo> root = criteriaQuery.from(Laudo.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("accessionNo"), accessionNo));

        List<Laudo> list = entityManager.createQuery(criteriaQuery).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

}
