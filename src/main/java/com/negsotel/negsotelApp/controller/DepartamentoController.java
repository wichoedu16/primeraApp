package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.DepartamentoEntity;
import com.negsotel.negsotelApp.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/departamento-admin/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;
    @GetMapping("")
    public ResponseEntity<Page<DepartamentoEntity>> getAll(Pageable pageable) {
        Page<DepartamentoEntity> departamentos = departamentoService.getAll(pageable);
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/{id}")
    ResponseEntity<DepartamentoEntity> getByDepartamentoId(@PathVariable Long id){
        DepartamentoEntity departamento = departamentoService.getById(id);
        return new ResponseEntity<>(departamento, HttpStatus.FOUND);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("")
    public ResponseEntity<DepartamentoEntity> crear(@RequestBody DepartamentoEntity departamento) {
        DepartamentoEntity departamentoGuardado = departamentoService.createDepartamento(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoGuardado);
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Actualizado exitosamente")
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoEntity> actualizar(@PathVariable Long id, @RequestBody DepartamentoEntity departamento) {
        DepartamentoEntity departamentoEditado = departamentoService.updateDepartamento(id, departamento);
        return ResponseEntity.status(HttpStatus.OK).body(departamentoEditado);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        departamentoService.deleteDepartamento(id);
    }

}
