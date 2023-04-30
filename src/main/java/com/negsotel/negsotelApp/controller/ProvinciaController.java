package com.negsotel.negsotelApp.controller;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.entity.ProvinciaEntity;
import com.negsotel.negsotelApp.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/provincia-admin/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("")
    public ResponseEntity<List<ProvinciaEntity>> getAll() {
        List<ProvinciaEntity> provincias = provinciaService.getAll();
        return ResponseEntity.ok(provincias);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProvinciaEntity> getById(@PathVariable Long id){
        ProvinciaEntity provincia = provinciaService.getById(id);
        return ResponseEntity.ok(provincia);
    }

    @GetMapping("/{id}/ciudades")
    ResponseEntity<List<CiudadEntity>> getCiudadesByProvincia(@PathVariable Long id){
        List<CiudadEntity> ciudades = provinciaService.geByProvinciaId(id);
        return ResponseEntity.ok(ciudades);
    }
}
