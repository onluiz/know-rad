package br.com.knowrad.service;

import br.com.knowrad.dto.PacienteDTO;
import br.com.knowrad.entity.Paciente;

import java.util.List;

public interface PacienteService {
    void persist(Paciente p);
    void merge(Paciente p);
    List<Paciente> findAll();
    List<PacienteDTO> findAllDTO();
    Paciente findById(final Long id);
    PacienteDTO findDTOById(Long id);
    Paciente findByPatId(String patId);
    PacienteDTO findDTOByPatId(String patId);
    PacienteDTO findByPatIdInList(String patId);
    List<PacienteDTO> getStaticList();
}
