package com.example.hospitalNuevo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalNuevo.model.ingreso;


@Repository
public interface Iingreso extends CrudRepository< ingreso , String>{

    @Query ("SELECT m FROM Ingreso m WHERE m.habitacion LIKE %?1%")
     List<ingreso>filtroIngreso(String filtro);
}