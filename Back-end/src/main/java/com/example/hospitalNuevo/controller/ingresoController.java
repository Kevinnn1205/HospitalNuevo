package com.example.hospitalNuevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalNuevo.interfaceService.IingresoService;
import com.example.hospitalNuevo.model.ingreso;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/v1/ingreso/")
@RestController
public class ingresoController {

    @Autowired
    private IingresoService ingresoService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("ingreso") ingreso ingreso) {
        var listaIngreso = ingresoService.findAll()
                .stream().filter(Ingreso -> ingreso.getCama()
                        .equals(ingreso.getCama()));
        if (listaIngreso.count() != 0) {
            return new ResponseEntity<>("El ingreso ya existe", HttpStatus.BAD_REQUEST);
        }
        //verificar que el campo documento de identidad sea diferente vacio
        if (ingreso.getHabitacion().equals("")) {

            return new ResponseEntity<>("El campo habitacion es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (ingreso.getCama().equals("")) {
            
            return new ResponseEntity<>("El campo cama es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getPaciente().equals("")) {
            
            return new ResponseEntity<>("El campo paciente es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getMedico().equals("")) {
            
            return new ResponseEntity<>("El campo medico es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getFecha_ingreso().equals("")) {
            
            return new ResponseEntity<>("El campo fecha ingreso es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getFecha_salida().equals("")) {
            
            return new ResponseEntity<>("El campo fecha salida es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getEstado().equals("")) {
            
            return new ResponseEntity<>("El campo estado es obligatorio", HttpStatus.BAD_REQUEST);
        }
          // todo bien
          ingresoService.save(ingreso); 
          return new ResponseEntity<>(ingreso, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaIngreso = ingresoService.findAll();
        return new ResponseEntity<>(listaIngreso, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
        var listaingreso = ingresoService.filtroIngreso(filtro);
        return new ResponseEntity<>(listaingreso, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var ingreso = ingresoService.findOne(id);
        return new ResponseEntity<>(ingreso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var ingreso = ingresoService.findOne(id).get();
        if (ingreso != null) {
            if (ingreso.getEstado().equals("H")) {

                ingreso.setEstado("D");
                return new ResponseEntity<>("Se ha deshabilitado correctamente", HttpStatus.OK);
            } else
            ingreso.setEstado("H");
            return new ResponseEntity<>("Se ha habilitado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminarPermanente/{id}")
        public ResponseEntity<Object> deleteForever(@PathVariable String id) {
        ingresoService.deleteForever(id);
        return new ResponseEntity<>("Registro eliminado Permanentemente", HttpStatus.OK);
     }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("ingreso") ingreso IngresoUpdate) {
        var ingreso = ingresoService.findOne(id).get();
        if (ingreso != null) {

            ingreso.setHabitacion(IngresoUpdate.getHabitacion());
            ingreso.setCama(IngresoUpdate.getCama());
            ingreso.setPaciente(IngresoUpdate.getPaciente());
            ingreso.setMedico(IngresoUpdate.getMedico());
            ingreso.setFecha_ingreso(IngresoUpdate.getFecha_ingreso());
            ingreso.setFecha_salida(IngresoUpdate.getFecha_salida());
            ingreso.setEstado(IngresoUpdate.getEstado());

            ingresoService.save(ingreso);
            return new ResponseEntity<>(ingreso, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error Ingreso NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
