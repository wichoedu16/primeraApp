package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.entity.DepartamentoEntity;
import com.negsotel.negsotelApp.repository.CargoRepository;
import com.negsotel.negsotelApp.repository.DepartamentoRepository;
import com.negsotel.negsotelApp.service.CargoService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/cargo-admin")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;
    private DepartamentoRepository departamentoRepository;
    private CargoService cargoService;

    @GetMapping("/cargos")
    ResponseEntity<List<CargoEntity>> getAll(){
        List<CargoEntity> cargos = cargoRepository.findAll();
        return new ResponseEntity<>(cargos, HttpStatus.OK);
    }

    @GetMapping("/cargos/{id}")
    CargoEntity getCargoById(@PathVariable Long id) {
        return cargoRepository.findById(id).orElse(null);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("/cargos")
    CargoEntity create(@RequestBody CargoEntity cargo){
        cargo.setFechaCreacion(LocalDateTime.now());
        DepartamentoEntity departamento = departamentoRepository.findById(cargo.getDepartamento().getId()).orElse(null);
        cargo.setDepartamento(departamento);
        return cargoRepository.save(cargo);
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Actualizado exitosamente")
    @PutMapping("/cargos/{id}")
    void update(@PathVariable Long id, @RequestBody CargoEntity cargo) {
        cargo.setId(id);
        cargo.setFechaModifica(LocalDateTime.now());
        cargoRepository.save(cargo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cargos/{id}")
    void delete(@PathVariable Long id){
        cargoRepository.deleteById(id);
    }
}
