package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.entity.patologia.PalavraChave;
import br.com.knowrad.entity.patologia.Patologia;
import br.com.knowrad.entity.patologia.PatologiaPalavraChave;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatologiaPalavraChaveDAOImpl extends AbstractDAO<PatologiaPalavraChave> implements PatologiaPalavraChaveDAO {

    public PatologiaPalavraChave findByIds(Long idPatologia, Long idPalavraChave) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PatologiaPalavraChave> criteriaQuery = criteriaBuilder.createQuery(PatologiaPalavraChave.class);

        final Root<PatologiaPalavraChave> root = criteriaQuery.from(PatologiaPalavraChave.class);
        Join<PatologiaPalavraChave, Patologia> patologia = root.join("patologia");
        Join<PatologiaPalavraChave, PalavraChave> palavraChave = root.join("palavraChave");

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(patologia.get("idPatologia"), idPatologia));
        predicates.add(criteriaBuilder.equal(palavraChave.get("idPalavraChave"), idPalavraChave));
        criteriaQuery.where(predicates.toArray(new Predicate[] {}));

        List<PatologiaPalavraChave> list = entityManager.createQuery(criteriaQuery).getResultList();

        if(!Util.verifyList(list))
            return null;

        return list.get(0);
    }

    public List<PatologiaPalavraChave> findAllByIdPatologia(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PatologiaPalavraChave> criteriaQuery = criteriaBuilder.createQuery(PatologiaPalavraChave.class);
        final Root<PatologiaPalavraChave> root = criteriaQuery.from(PatologiaPalavraChave.class);

        root.fetch("patologia");
        root.fetch("palavraChave");

        criteriaQuery.where(criteriaBuilder.equal(root.get("patologia").get("idPatologia"), id));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
