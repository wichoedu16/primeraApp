package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.entity.EmpleadoEntity;
import com.negsotel.negsotelApp.repository.EmpleadoRepository;
import com.negsotel.negsotelApp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado-admin/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("")
    public ResponseEntity<List<EmpleadoEntity>> getAll() {
        List<EmpleadoEntity> empleados = empleadoService.findAll();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> getEmpleadoById(@PathVariable Long id) {
        EmpleadoEntity empleado = empleadoService.getById(id);
        return ResponseEntity.ok(empleado);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("")
    public ResponseEntity<EmpleadoEntity> crearEmpleado(@RequestBody EmpleadoEntity empleado) {
        EmpleadoEntity empleadoGuardado = empleadoService.createEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Actualizado exitosamente")
    @PutMapping("/empleado/{id}")
    public ResponseEntity<EmpleadoEntity> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoEntity empleado) {
        EmpleadoEntity empleadoEditado = empleadoService.updateEmpleado(id, empleado);
        return ResponseEntity.status(HttpStatus.OK).body(empleadoEditado);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/empleados/{id}")
    void delete(@PathVariable Long id){
        empleadoService.deleteById(id);
    }
}

