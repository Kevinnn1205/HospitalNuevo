package com.example.hospitalNuevo.interfaceService;

import java.util.List;
import java.util.Optional;


import com.example.hospitalNuevo.model.ingreso;


public interface IingresoService {

    public String save(ingreso ingreso);

    public List<ingreso> findAll();

    public List<ingreso> filtroIngreso (String filtro);

    public Optional<ingreso> findOne(String id);

    public int deleteForever(String id);
}