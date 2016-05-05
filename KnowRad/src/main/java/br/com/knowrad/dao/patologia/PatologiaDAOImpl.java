package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaFilter;
import br.com.knowrad.entity.patologia.Patologia;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatologiaDAOImpl extends AbstractDAO<Patologia> implements PatologiaDAO {

    public DatatableResponse<PatologiaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, PatologiaFilter filter) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Patologia> criteriaQuery = criteriaBuilder.createQuery(Patologia.class);
        final Root<Patologia> root = criteriaQuery.from(Patologia.class);

        final List<Predicate> predicates = new ArrayList<Predicate>();

        if (datatableRequest.getsSearch() != null && !("").equals(datatableRequest.getsSearch())) {
            final List<Predicate> predicateSearch = new ArrayList<Predicate>();
            predicateSearch.add(criteriaBuilder.like(criteriaBuilder.upper((Expression) root.get("nome")), ("%" + datatableRequest.getsSearch() + "%").toUpperCase()));
            predicates.add(criteriaBuilder.or(predicateSearch.toArray(new Predicate[] {})));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[] {}));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

        List<Patologia> list = entityManager.createQuery(criteriaQuery).setFirstResult(datatableRequest.getiDisplayStart()).
                setMaxResults(datatableRequest.getiDisplayLength()).getResultList();

        Long totalFiltered = getTotalFiltered(criteriaQuery);

        List<PatologiaDTO> listDTO = new ArrayList<PatologiaDTO>();
        for (Patologia patologia : list) {
            PatologiaDTO dto = new PatologiaDTO();
            dto.setId(patologia.getId());
            dto.setNome(patologia.getNome());
            listDTO.add(dto);
        }

        DatatableResponse<PatologiaDTO> listResponse = new DatatableResponse<PatologiaDTO>();
        listResponse.setsEcho(datatableRequest.getsEcho());
        listResponse.setiTotalDisplayRecords(totalFiltered);
        listResponse.setAaData(listDTO);

        return listResponse;
    }

    Long getTotalFiltered(CriteriaQuery<Patologia> criteriaQuery) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<?> entityRoot = countCriteria.from(criteriaQuery.getResultType());
        countCriteria.select(builder.count(entityRoot));
        countCriteria.where(criteriaQuery.getRestriction());
        return entityManager.createQuery(countCriteria).getSingleResult();
    }
}
