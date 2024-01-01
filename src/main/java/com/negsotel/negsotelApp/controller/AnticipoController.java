package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.controller.dto.AnticipoDTO;
import com.negsotel.negsotelApp.entity.AnticipoEntity;
import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.repository.AnticipoRepository;
import com.negsotel.negsotelApp.service.AnticipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/anticipo-admin/anticipos")
public class AnticipoController {

    @Autowired
    private AnticipoService anticipoService;

    @GetMapping("")
    ResponseEntity<List<AnticipoEntity>> getAll(){
        List<AnticipoEntity> anticipos = anticipoService.findAll();
        return new ResponseEntity<>(anticipos, HttpStatus.OK);
    }

    @GetMapping("/empleado/{idEmpleado}")
    ResponseEntity<List<AnticipoDTO>> getAllByEmpleadoId(@PathVariable Long idEmpleado){
        List<AnticipoDTO> anticipos = anticipoService.getAllByEmpleadoId(idEmpleado);
        return new ResponseEntity<>(anticipos, HttpStatus.FOUND);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creado exitosamente")
    @PostMapping("")
    public ResponseEntity<AnticipoEntity> createAnticipo(@RequestBody AnticipoEntity anticipo) {
        AnticipoEntity anticipoNuevo = anticipoService.create(anticipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(anticipoNuevo);
    }

/*
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

 */
}
