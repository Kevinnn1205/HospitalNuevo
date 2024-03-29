package com.example.hospitalNuevo.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.hospitalNuevo.model.paciente;

public interface IpacienteService {

    public String save(paciente paciente);

    public List<paciente> findAll();

    public List<paciente> filtroPaciente (String filtro);

    public Optional<paciente> findOne(String id);

    public int deleteForever(String id);
}
