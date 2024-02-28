package com.example.hospitalNuevo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalNuevo.model.paciente;


@Repository
public interface Ipaciente extends CrudRepository< paciente , String>{

    // ?1 es la primera variable

    @Query("SELECT m FROM paciente m WHERE m.primer_nombre LIKE %?1% OR documento_identidad LIKE %?1%")
    List<paciente>filtroPaciente(String filtro);
    
    @Query("SELECT m FROM paciente m WHERE m.estado LIKE %?1%")
    List<paciente>filtropaciente(char estado);
    
}