package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.entity.EmpleadoEntity;
import com.negsotel.negsotelApp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado-admin")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    ResponseEntity<List<EmpleadoEntity>> getAll(){
        List<EmpleadoEntity> empleados = empleadoRepository.findAll();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/empleados/{id}")
    EmpleadoEntity getEmpleadoById(@PathVariable Long id) {
        return (EmpleadoEntity) empleadoRepository.findById(id).orElse(null);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("/empleados")
    EmpleadoEntity create(@RequestBody EmpleadoEntity empleado){
        empleado.setFechaCreacion(LocalDateTime.now());
        return (EmpleadoEntity) empleadoRepository.save(empleado);
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/empleados/{id}")
    void delete(@PathVariable Long id){
        empleadoRepository.deleteById(id);
    }
}

