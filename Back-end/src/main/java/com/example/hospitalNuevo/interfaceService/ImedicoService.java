package com.example.hospitalNuevo.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.hospitalNuevo.model.medico;

public interface ImedicoService {

    public String save(medico medico);

    public List<medico> findAll();

    public List<medico> filtroMedico(String filtro);

    public Optional<medico> findOne(String id);

    public int deleteForever(String id);
}
