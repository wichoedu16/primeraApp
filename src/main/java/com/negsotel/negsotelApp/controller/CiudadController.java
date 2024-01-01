package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ciudad-admin")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/ciudades")
    public ResponseEntity<List<CiudadEntity>> getAll() {
        List<CiudadEntity> ciudades = ciudadService.getAll();
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/ciudad/{codigoCiudad}")
    ResponseEntity<CiudadEntity> getByCodigoCiduad(@PathVariable Long codigoCiudad){
        CiudadEntity ciudad = ciudadService.getByCodigo(codigoCiudad);
        return ResponseEntity.ok(ciudad);
    }

    @GetMapping("/{codigoCiudad}/provincia/{provinciaId}")
    ResponseEntity<CiudadEntity> getById(@PathVariable Long provinciaId, Long codigoCiudad){
        CiudadEntity ciudad = ciudadService.getByIds(provinciaId, codigoCiudad);
        return ResponseEntity.ok(ciudad);
    }
    @GetMapping("/provincia/{provinciaId}")
    ResponseEntity<List<CiudadEntity>> getByProvinciaId(@PathVariable Long provinciaId){
        List<CiudadEntity> ciudades = ciudadService.geByProvinciaId(provinciaId);
        return ResponseEntity.ok(ciudades);
    }
}
