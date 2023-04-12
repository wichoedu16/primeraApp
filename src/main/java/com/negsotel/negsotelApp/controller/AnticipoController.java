package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.AnticipoEntity;
import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.repository.AnticipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/anticipo-admin")
public class AnticipoController {

    @Autowired
    private AnticipoRepository anticipoRepository;

    @GetMapping("/anticipos")
    ResponseEntity<List<AnticipoEntity>> getAll(){
        List<AnticipoEntity> anticipos = anticipoRepository.findAll();
        return new ResponseEntity<>(anticipos, HttpStatus.OK);
    }

    @GetMapping("/anticipos/{id}")
    AnticipoEntity getBYId(@PathVariable Long id) {
        return anticipoRepository.findById(id).orElse(null);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("/anticipos")
    AnticipoEntity create(@RequestBody AnticipoEntity anticipo){
        anticipo.setFechaCreacion(LocalDateTime.now());
        return anticipoRepository.save(anticipo);
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Actualizado exitosamente")
    @PutMapping("/anticipos/{id}")
    void update(@PathVariable Long id, @RequestBody AnticipoEntity anticipo) {
        anticipo.setId(id);
        anticipo.setFechaModifica(LocalDateTime.now());
        anticipoRepository.save(anticipo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/anticipos/{id}")
    void delete(@PathVariable Long id){
        anticipoRepository.deleteById(id);
    }
}
