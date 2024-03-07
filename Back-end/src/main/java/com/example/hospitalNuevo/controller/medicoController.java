package com.example.hospitalNuevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalNuevo.interfaceService.ImedicoService;
import com.example.hospitalNuevo.model.medico;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/v1/medico/")
@RestController
public class medicoController {

    @Autowired
    private ImedicoService medicoService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("medico") medico medico) {

        // verificar que no exista el documento de identidad
        // var listamedico = medicoService.findAll()
        //         .stream().filter(Medico -> medico.getDocumento_identidad()
        //                 .equals(medico.getDocumento_identidad()));
        // if (listamedico.count() != 0) {
        //     return new ResponseEntity<>("El medico ya existe", HttpStatus.BAD_REQUEST);
        // }
        //verificar que el campo documento de identidad sea diferente vacio
        //Añadir campos obligatorios
        if (medico.getDocumento_identidad().equals("")) {

            return new ResponseEntity<>("El documento de identidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getPrimer_nombre().equals("")) {
            
            return new ResponseEntity<>("El primer nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getPrimer_apellido().equals("")) {
            
            return new ResponseEntity<>("El primer apellido es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getCelular().equals("")) {
            
            return new ResponseEntity<>("El numero de celular es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getCorreo().equals("")) {
            
            return new ResponseEntity<>("La direccion de correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        
        medicoService.save(medico);
        return new ResponseEntity<>(medico, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listamedico = medicoService.findAll();
        return new ResponseEntity<>(listamedico, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listamedico = medicoService.filtroMedico(filtro);
        return new ResponseEntity<>(listamedico, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var medico = medicoService.findOne(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var medico = medicoService.findOne(id).get();
        if (medico != null) {
            if (medico.getEstado().equals("H")) {

                medico.setEstado("D");
                medicoService.save(medico);
                return new ResponseEntity<>("Se ha deshabilitado correctamente", HttpStatus.OK);
            } else
                medico.setEstado("H");
                medicoService.save(medico);
            return new ResponseEntity<>("Se ha habilitado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

     @DeleteMapping("/eliminarPermanente/{id}")
        public ResponseEntity<Object> deleteForever(@PathVariable String id) {
        medicoService.deleteForever(id);
        return new ResponseEntity<>("Registro eliminado Permanentemente", HttpStatus.OK);
     }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("medico") medico MedicoUpdate) {
        var medico = medicoService.findOne(id).get();
        if (medico != null) {

            medico.setDocumento_identidad(MedicoUpdate.getDocumento_identidad());
            medico.setPrimer_nombre(MedicoUpdate.getPrimer_nombre());
            medico.setSegundo_nombre(MedicoUpdate.getSegundo_nombre());
            medico.setPrimer_apellido(MedicoUpdate.getPrimer_apellido());
            medico.setSegundo_apellido(MedicoUpdate.getSegundo_apellido());
            medico.setCelular(MedicoUpdate.getCelular());
            medico.setCorreo(MedicoUpdate.getCorreo());
            medico.setEstado(MedicoUpdate.getEstado());

            medicoService.save(medico);
            return new ResponseEntity<>(medico, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error Medico NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}

