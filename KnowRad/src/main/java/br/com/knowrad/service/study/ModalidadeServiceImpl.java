package br.com.knowrad.service.study;

import br.com.knowrad.dao.study.ModalidadeDAO;
import br.com.knowrad.dto.study.ModalidadeDTO;
import br.com.knowrad.entity.study.Modalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ModalidadeServiceImpl implements ModalidadeService {

    @Autowired
    private ModalidadeDAO modalidadeDAO;

    public List<Modalidade> findAll() {
        return modalidadeDAO.findAll();
    }

    public List<ModalidadeDTO> findAllDTO() {
        return new ArrayList<ModalidadeDTO>() {{
            for(Modalidade modalidade : findAll())
                add(new ModalidadeDTO(modalidade.getIdModalidade(), modalidade.getModalidade()));
        }};
    }

    public Modalidade findById(Long id) {
        return modalidadeDAO.findById(id);
    }

}
