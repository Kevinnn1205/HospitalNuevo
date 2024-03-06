package com.example.hospitalNuevo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalNuevo.interfaceService.ImedicoService;
import com.example.hospitalNuevo.interfaces.Imedico;
import com.example.hospitalNuevo.model.medico;

@Service

public class medicoService implements ImedicoService {

    @Autowired
    private Imedico data;

    @Override
    public String save(medico medico) {
        data.save(medico);
        return medico.getId_medico();
    }

    @Override
    public List<medico> findAll() {
        List<medico> listaMedico = (List<medico>) data.findAll();
        return listaMedico;
    }

    @Override
    public List<medico> filtroMedico(String filtro) {
        List<medico> listaMedico =data.filtroMedico(filtro);
        return listaMedico;
    }

    @Override
    public Optional<medico> findOne(String id) {
        Optional<medico> Medico = data.findById(id);
        return Medico;
    }

    @Override
    public int deleteForever(String id) {
        data.deleteById(id);
        return 1;
    }
}
