package com.example.hospitalNuevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalNuevo.interfaceService.IpacienteService;
import com.example.hospitalNuevo.model.paciente;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/v1/paciente/")
@RestController
public class pacienteController {

    @Autowired
    private IpacienteService pacienteService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("paciente") paciente paciente) {
        var listaPaciente = pacienteService.findAll()
                .stream().filter(Paciente -> paciente.getDocumento_identidad()
                        .equals(paciente.getDocumento_identidad()));
        if (listaPaciente.count() != 0) {
            return new ResponseEntity<>("El paciente ya existe", HttpStatus.BAD_REQUEST);
        }
        // verificar que el campo documento de identidad sea diferente vacio
        if (paciente.getDocumento_identidad().equals("")) {

            return new ResponseEntity<>("El documento de identidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getPrimer_nombre().equals("")) {

            return new ResponseEntity<>("El primer nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getPrimer_apellido().equals("")) {

            return new ResponseEntity<>("El primer apellido es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getCelular().equals("")) {

            return new ResponseEntity<>("El numero de celular es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getCorreo().equals("")) {

            return new ResponseEntity<>("La direccion de correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getNombre_persona_contacto().equals("")) {

            return new ResponseEntity<>("El Nombre Persona Contacto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getTelefono_persona_contacto().equals("")) {

            return new ResponseEntity<>(" El Telefono Persona Contacto es un campo obligatorio",
                    HttpStatus.BAD_REQUEST);
        }

        // todo bien
        pacienteService.save(paciente);
        return new ResponseEntity<>(paciente, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listapaciente = pacienteService.findAll();
        return new ResponseEntity<>(listapaciente, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listapaciente = pacienteService.filtroPaciente(filtro);
        return new ResponseEntity<>(listapaciente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var paciente = pacienteService.findOne(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var paciente = pacienteService.findOne(id).get();
        if (paciente != null) {
            if (paciente.getEstado().equals("H")) {

                paciente.setEstado("D");
                return new ResponseEntity<>("Se ha deshabilitado correctamente el paciente", HttpStatus.OK);
            } else
                paciente.setEstado("H");
            return new ResponseEntity<>("Se ha habilitado correctamente el paciente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminarPermanente/{id}")
    public ResponseEntity<Object> deleteForever(@PathVariable String id) {
        pacienteService.deleteForever(id);
        return new ResponseEntity<>("Registro eliminado Permanentemente", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("Paciente") paciente PacienteUpdate) {
        var paciente = pacienteService.findOne(id).get();
        if (paciente != null) {

            paciente.setDocumento_identidad(PacienteUpdate.getDocumento_identidad());
            paciente.setPrimer_nombre(PacienteUpdate.getPrimer_nombre());
            paciente.setSegundo_nombre(PacienteUpdate.getSegundo_nombre());
            paciente.setPrimer_apellido(PacienteUpdate.getPrimer_apellido());
            paciente.setSegundo_apellido(PacienteUpdate.getSegundo_apellido());
            paciente.setCelular(PacienteUpdate.getCelular());
            paciente.setCorreo(PacienteUpdate.getCorreo());
            paciente.setNombre_persona_contacto(PacienteUpdate.getNombre_persona_contacto());
            paciente.setTelefono_persona_contacto(PacienteUpdate.getTelefono_persona_contacto());
            paciente.setEstado(PacienteUpdate.getEstado());

            pacienteService.save(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error Paciente NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
