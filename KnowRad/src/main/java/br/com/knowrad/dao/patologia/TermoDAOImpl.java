package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.dto.patologia.TermoDTO;
import br.com.knowrad.entity.patologia.Termo;
import br.com.knowrad.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TermoDAOImpl extends AbstractDAO<Termo> implements TermoDAO {

    public List<Termo> findListByIdPatologia(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Termo> criteriaQuery = criteriaBuilder.createQuery(Termo.class);
        final Root<Termo> root = criteriaQuery.from(Termo.class);
//        root.fetch("patologia");
        criteriaQuery.where(criteriaBuilder.equal(root.get("patologia").get("id"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<TermoDTO> findAllDTO() {
        String sql = " SELECT nome_termo, id_patologia FROM termo ";
        Query q = entityManager.createNativeQuery(sql);
        Iterator iterator = q.getResultList().iterator();

        List<TermoDTO> listDTO = new ArrayList<TermoDTO>();

        while (iterator.hasNext()) {
            Object[] object = (Object[]) iterator.next();
            TermoDTO dto = new TermoDTO();
            dto.setNomeTermo(Util.verifyString(object[0]));
            dto.setIdPatologia(Util.verifyLong(object[1]));
            listDTO.add(dto);
        }

        return listDTO;
    }
}
