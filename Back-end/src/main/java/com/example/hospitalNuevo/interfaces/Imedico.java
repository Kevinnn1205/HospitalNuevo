package com.example.hospitalNuevo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalNuevo.model.medico;


@Repository
public interface Imedico extends CrudRepository< medico , String>{
    

    //?1 es la primera variable (?2,?3 y asi sucesivamente)
    @Query("SELECT m FROM medico m WHERE m.primer_nombre LIKE %?1% OR m.documento_identidad LIKE %?1%")
    List<medico> filtroMedico(String filtro);
    
    @Query("SELECT m FROM medico m WHERE m.estado LIKE %?1%")
    List<medico> filtroMedico(char estado);
}
