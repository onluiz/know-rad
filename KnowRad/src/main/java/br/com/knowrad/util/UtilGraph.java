package br.com.knowrad.util;

import br.com.knowrad.dto.*;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.dto.patologia.PatologiaResponse;
import br.com.knowrad.service.PacienteService;
import br.com.knowrad.service.patologia.PatologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class UtilGraph {

    @Autowired
    private PatologiaService patologiaService;

    @Autowired
    private PacienteService pacienteService;

    public EdgeDTO getEdgeDTO(Object source, Object target) {
        EdgeDTO edgeDTO = new EdgeDTO();
        edgeDTO.setSelected(Boolean.FALSE);
        edgeDTO.setSource(Util.verifyString(source));
        edgeDTO.setTarget(Util.verifyString(target));
        edgeDTO.setCanonicalName("");
        edgeDTO.setSUID(edgeDTO.getId());
        edgeDTO.setName(edgeDTO.getCanonicalName());
        edgeDTO.setInteraction("cc");
        edgeDTO.setShared_interaction("cc");
        edgeDTO.setShared_name(edgeDTO.getCanonicalName());
        return edgeDTO;
    }

    public EdgeResponse getEdgeResponse(Object source, Object target) {
        EdgeResponse edgeResponse = new EdgeResponse();
        edgeResponse.setData(getEdgeDTO(source, target));
        edgeResponse.setSelected(Boolean.FALSE);
        return edgeResponse;
    }

    public PatologiaResponse getPatologiaResponse(PatologiaDTO patologiaDTO) {
        PatologiaResponse patologiaResponse = new PatologiaResponse();
        patologiaResponse.setData(patologiaDTO);
        patologiaResponse.setPosition(new Position());
        patologiaResponse.setSelected(Boolean.FALSE);
        return patologiaResponse;
    }

    public List<PatologiaResponse> getListPatologiaResponse() {
        List<PatologiaResponse> list = new ArrayList<PatologiaResponse>();
        for(PatologiaDTO patologiaDTO : patologiaService.getStaticList())
            list.add(getPatologiaResponse(patologiaDTO));
        return list;
    }

    public LaudoResponse getLaudoResponse(LaudoDTO laudoDTO) {
        laudoDTO.setSelected(Boolean.FALSE);
        laudoDTO.setCytoscape_alias_list(new String[]{laudoDTO.getTitulo()});
        laudoDTO.setCanonicalName(laudoDTO.getTitulo());
        laudoDTO.setSUID(laudoDTO.getId());
        laudoDTO.setNodeType("Cheese");
        laudoDTO.setName(laudoDTO.getTitulo());
        laudoDTO.setShared_name(laudoDTO.getTitulo());
        laudoDTO.setNodeTypeFormatted("Cheese");

        LaudoResponse laudoResponse = new LaudoResponse();
        laudoResponse.setData(laudoDTO);
        laudoResponse.setPosition(new Position());
        laudoResponse.setSelected(Boolean.FALSE);
        return laudoResponse;
    }

    public Double getRandomArbitrary(Double min, Double max) {
        return Math.random() * (max - min) + min;
    }

    public PacienteResponse getPacienteResponse(String patId) {
        PacienteDTO pacienteDTO = pacienteService.findByPatIdInList(patId);
        pacienteDTO.setId(Util.verifyLong(pacienteDTO.getPatId()));

        Position position = new Position();
        position.setX(getRandomArbitrary(6000.0, 100000.0));
        position.setY(getRandomArbitrary(6000.0, 100000.0));

        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setData(pacienteDTO);
        pacienteResponse.setPosition(position);
        pacienteResponse.setSelected(Boolean.FALSE);
        return pacienteResponse;
    }

    public PacienteResponse updateListPacienteResponse(List<PacienteResponse> listPacienteResponse, String patId) {
        int count = 0;
        while(count <= listPacienteResponse.size() - 1 && listPacienteResponse.size() > 0) {
            PacienteResponse response = listPacienteResponse.get(count);
            if(response.getData().getPatId().equals(patId)) {
                return response;
            }
            count++;
        }

        PacienteResponse pacienteResponse = getPacienteResponse(patId);
        listPacienteResponse.add(pacienteResponse);

        return pacienteResponse;
    }
}
