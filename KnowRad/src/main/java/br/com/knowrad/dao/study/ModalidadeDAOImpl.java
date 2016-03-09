package br.com.knowrad.dao.study;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.entity.study.Modalidade;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ModalidadeDAOImpl extends AbstractDAO<Modalidade> implements ModalidadeDAO {

    public List<ModalidadeDTO> search(String searchText, Integer limit) {
        if(searchText == null || searchText.equals(""))
            return new ArrayList<ModalidadeDTO>();

        if(limit == null || limit == 0)
            return new ArrayList<ModalidadeDTO>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT m.id_modalidade, m.modalidade FROM modalidade m ");
        sql.append(" WHERE m.modalidade LIKE upper(concat('%', :searchText, '%')) ");
        sql.append(" ORDER BY m.modalidade ASC ");
        sql.append(" LIMIT :limit ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("searchText", searchText);
        query.setParameter("limit", limit);

        Iterator iterator = query.getResultList().iterator();
        List<ModalidadeDTO> listDTO = new ArrayList<ModalidadeDTO>();

        while (iterator.hasNext()) {
            Object[] object = (Object[]) iterator.next();
            ModalidadeDTO dto = new ModalidadeDTO();
            dto.setIdModalidade(Long.valueOf(String.valueOf(object[0])));
            dto.setModalidade(String.valueOf(object[1]));
            listDTO.add(dto);
        }

        return listDTO;
    }

}
