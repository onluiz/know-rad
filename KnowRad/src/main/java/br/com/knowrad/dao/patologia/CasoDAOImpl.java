package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.dto.datatable.DatatableRequest;
import br.com.knowrad.dto.datatable.DatatableResponse;
import br.com.knowrad.dto.patologia.CasoDTO;
import br.com.knowrad.dto.patologia.CasoFilterDTO;
import br.com.knowrad.entity.patologia.Caso;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CasoDAOImpl extends AbstractDAO<Caso> implements CasoDAO {

    public DatatableResponse<CasoDTO> findListDatatableByFilter(DatatableRequest datatableRequest, CasoFilterDTO filter) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Caso> criteriaQuery = criteriaBuilder.createQuery(Caso.class);
        final Root<Caso> root = criteriaQuery.from(Caso.class);

        final List<Predicate> predicates = new ArrayList<Predicate>();

        if (datatableRequest.getsSearch() != null && !("").equals(datatableRequest.getsSearch())) {
            final List<Predicate> predicateSearch = new ArrayList<Predicate>();
            predicateSearch.add(criteriaBuilder.like(criteriaBuilder.upper((Expression) root.get("titulo")), ("%" + datatableRequest.getsSearch() + "%").toUpperCase()));
            predicateSearch.add(criteriaBuilder.like(criteriaBuilder.upper((Expression) root.get("laudo")), ("%" + datatableRequest.getsSearch() + "%").toUpperCase()));
            predicates.add(criteriaBuilder.or(predicateSearch.toArray(new Predicate[] {})));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[] {}));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("titulo")));

        List<Caso> list = entityManager.createQuery(criteriaQuery).setFirstResult(datatableRequest.getiDisplayStart()).
                setMaxResults(datatableRequest.getiDisplayLength()).getResultList();

        Long totalFiltered = getTotalFiltered(criteriaQuery);

        List<CasoDTO> listDTO = new ArrayList<CasoDTO>();
        for (Caso caso : list) {
            CasoDTO dto = new CasoDTO();
            dto.setIdCaso(caso.getIdCaso());
            dto.setTitulo(caso.getTitulo());
            dto.setLaudo(caso.getLaudo());
            listDTO.add(dto);
        }

        DatatableResponse<CasoDTO> listResponse = new DatatableResponse<CasoDTO>();
        listResponse.setsEcho(datatableRequest.getsEcho());
        listResponse.setiTotalDisplayRecords(totalFiltered);
        listResponse.setAaData(listDTO);

        return listResponse;
    }

    private Long getTotalFiltered(CriteriaQuery<Caso> criteriaQuery) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<?> entityRoot = countCriteria.from(criteriaQuery.getResultType());
        countCriteria.select(builder.count(entityRoot));
        countCriteria.where(criteriaQuery.getRestriction());
        return entityManager.createQuery(countCriteria).getSingleResult();
    }

}
