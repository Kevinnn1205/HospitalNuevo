package com.example.hospitalNuevo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalNuevo.interfaceService.IpacienteService;
import com.example.hospitalNuevo.interfaces.Ipaciente;
import com.example.hospitalNuevo.model.paciente;

@Service

public class pacienteService implements IpacienteService {

    @Autowired
    private Ipaciente data;

    @SuppressWarnings("null")
    @Override
    public String save(paciente paciente) {
        data.save(paciente);
        return paciente.getId_paciente();
    }

    @Override
    public List<paciente> findAll() {
        List<paciente> listaPaciente = (List<paciente>) data.findAll();
        return listaPaciente;
    }

    @Override
    public List<paciente> filtroPaciente(String filtro) {
        List<paciente>listapaciente=data.filtroPaciente(filtro);
        return listapaciente;
    }


    @Override
    public Optional<paciente> findOne(String id) {
        @SuppressWarnings("null")
        Optional<paciente> Paciente = data.findById(id);
        return Paciente;
    }

    @SuppressWarnings("null")
    @Override
    public int deleteForever(String id) {
        data.deleteById(id);
        return 1;
    }
}
