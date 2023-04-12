package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.DepartamentoEntity;
import com.negsotel.negsotelApp.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/departamento-admin")
public class DepartamentoController {


    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/departamentos")
    ResponseEntity<List<DepartamentoEntity>> getAll(){
        List<DepartamentoEntity> departamentos = departamentoRepository.findAll();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @GetMapping("/departamentos/{id}")
    DepartamentoEntity getById(@PathVariable Long id) {
        return departamentoRepository.findById(id).orElse(null);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("/departamentos")
    DepartamentoEntity create(@RequestBody DepartamentoEntity departamento){
        departamento.setFechaCreacion(LocalDateTime.now());
        return departamentoRepository.save(departamento);
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Actualizado exitosamente")
    @PutMapping("/departamentos/{id}")
    void update(@PathVariable Long id, @RequestBody DepartamentoEntity departamento) {
        departamento.setId(id);
        departamento.setFechaModifica(LocalDateTime.now());
        departamentoRepository.save(departamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/departamentos/{id}")
    void delete(@PathVariable Long id){
        departamentoRepository.deleteById(id);
    }
}
