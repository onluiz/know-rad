package br.com.knowrad.dao;

import br.com.knowrad.entity.Paciente;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PacienteDAOImpl extends AbstractDAO<Paciente> implements PacienteDAO {

    public Paciente findByPatId(String patId) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Paciente> criteriaQuery = criteriaBuilder.createQuery(Paciente.class);
        final Root<Paciente> root = criteriaQuery.from(Paciente.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("patId"), patId));

        List<Paciente> list = entityManager.createQuery(criteriaQuery).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

}
