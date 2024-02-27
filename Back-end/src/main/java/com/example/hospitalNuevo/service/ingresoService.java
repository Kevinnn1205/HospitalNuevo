package com.example.hospitalNuevo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalNuevo.interfaceService.IingresoService;
import com.example.hospitalNuevo.interfaces.Iingreso;
import com.example.hospitalNuevo.model.ingreso;

@Service

public class ingresoService implements IingresoService {

    @Autowired
    private Iingreso data;

    @Override
    public String save(ingreso ingreso) {
        data.save(ingreso);
        return ingreso.getId_ingreso();
    }

    @Override
    public List<ingreso> findAll() {
        List<ingreso> listaIngreso = (List<ingreso>) data.findAll();
        return listaIngreso;
    }
    @Override
    public List<ingreso>  filtroIngreso(String filtro) {
        List<ingreso> listaIngreso = data.filtroIngreso(filtro);
        return listaIngreso;
    }

    @Override
    public Optional<ingreso> findOne(String id) {
        Optional<ingreso> ingreso = data.findById(id);
        return ingreso;
    }

    @Override
    public int deleteForever(String id) {
        data.deleteById(id);
        return 1;
    }
}
