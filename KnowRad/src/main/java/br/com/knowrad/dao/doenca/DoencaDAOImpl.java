package br.com.knowrad.dao.doenca;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.doenca.DoencaDTO;
import br.com.knowrad.dto.doenca.DoencaFilter;
import br.com.knowrad.entity.doenca.Doenca;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoencaDAOImpl extends AbstractDAO<Doenca> implements DoencaDAO {

    public DatatableResponse<DoencaDTO> findListDatatableByFilter(DatatableRequest datatableRequest, DoencaFilter filter) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Doenca> criteriaQuery = criteriaBuilder.createQuery(Doenca.class);
        final Root<Doenca> root = criteriaQuery.from(Doenca.class);

        final List<Predicate> predicates = new ArrayList<Predicate>();

        if (datatableRequest.getsSearch() != null && !("").equals(datatableRequest.getsSearch())) {
            final List<Predicate> predicateSearch = new ArrayList<Predicate>();
            predicateSearch.add(criteriaBuilder.like(criteriaBuilder.upper((Expression) root.get("nome")), ("%" + datatableRequest.getsSearch() + "%").toUpperCase()));
            predicates.add(criteriaBuilder.or(predicateSearch.toArray(new Predicate[] {})));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[] {}));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

        List<Doenca> list = entityManager.createQuery(criteriaQuery).setFirstResult(datatableRequest.getiDisplayStart()).
                setMaxResults(datatableRequest.getiDisplayLength()).getResultList();

        Long totalFiltered = getTotalFiltered(criteriaQuery);

        List<DoencaDTO> listDTO = new ArrayList<DoencaDTO>();
        for (Doenca doenca : list) {
            DoencaDTO dto = new DoencaDTO();
            dto.setId(doenca.getId());
            dto.setNome(doenca.getNome());
            listDTO.add(dto);
        }

        DatatableResponse<DoencaDTO> listResponse = new DatatableResponse<DoencaDTO>();
        listResponse.setsEcho(datatableRequest.getsEcho());
        listResponse.setiTotalDisplayRecords(totalFiltered);
        listResponse.setAaData(listDTO);

        return listResponse;
    }

    Long getTotalFiltered(CriteriaQuery<Doenca> criteriaQuery) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<?> entityRoot = countCriteria.from(criteriaQuery.getResultType());
        countCriteria.select(builder.count(entityRoot));
        countCriteria.where(criteriaQuery.getRestriction());
        return entityManager.createQuery(countCriteria).getSingleResult();
    }
}
