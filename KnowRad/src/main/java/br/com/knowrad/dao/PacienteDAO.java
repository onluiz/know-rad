package br.com.knowrad.dao;

import br.com.knowrad.entity.Paciente;

import java.util.List;

public interface PacienteDAO {
    void persist(Paciente p);
    List<Paciente> findAll();
    Paciente findById(final Long id);
    Paciente findByPatId(String patId);
}
