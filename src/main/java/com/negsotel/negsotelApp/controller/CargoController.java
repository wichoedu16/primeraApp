package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.service.CargoService;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/cargo-admin")
public class CargoController {

    @Autowired
    private CargoService cargoService;
    @GetMapping("/cargos")
    public ResponseEntity<Page<CargoEntity>> getAll(Pageable pageable) {
        Page<CargoEntity> cargos = cargoService.getAll(pageable);
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/departamento/{idDepartamento}")
    ResponseEntity<List<CargoEntity>> getByDepartamentoId(@PathVariable Long idDepartamento){
        List<CargoEntity> cargos = cargoService.geByDepartamentoId(idDepartamento);
        return new ResponseEntity<>(cargos, HttpStatus.FOUND);
    }

    @GetMapping("/cargos/{id}")
    ResponseEntity<CargoEntity> getCargoById(@PathVariable Long id) {
        CargoEntity cargo = cargoService.getById(id);
        return new ResponseEntity<>(cargo, HttpStatus.FOUND);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("/cargos")
    public ResponseEntity<CargoEntity> crearCargo(@RequestBody CargoEntity cargo) {
        CargoEntity cargoGuardado = cargoService.createCargo(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoGuardado);
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Actualizado exitosamente")
    @PutMapping("/cargos/{id}")
    public ResponseEntity<CargoEntity> actualizarCargo(@PathVariable Long id, @RequestBody CargoEntity cargo) {
        CargoEntity cargoEditado = cargoService.updateCargo(id, cargo);
        return ResponseEntity.status(HttpStatus.OK).body(cargoEditado);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cargos/{id}")
    void delete(@PathVariable Long id){
        cargoService.deleteCargo(id);
    }

}
