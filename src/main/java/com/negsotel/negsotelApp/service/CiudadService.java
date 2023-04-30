package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.repository.CiudadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;
    public List<CiudadEntity> getAll() {
        return ciudadRepository.findAll();
    }

    public CiudadEntity getById(Long id) {
        Optional<CiudadEntity> ciudad = ciudadRepository.findById(id);
        if (ciudad.isPresent()) {
            return ciudad.get();
        } else {
            throw new EntityNotFoundException("No se encontró la ciudad con ID " + id);
        }
    }

    public List<CiudadEntity> geByProvinciaId(Long provinciaId) {
        return ciudadRepository.findByProvinciaId(provinciaId);
    }
}
