package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.entity.ProvinciaEntity;
import com.negsotel.negsotelApp.repository.CiudadRepository;
import com.negsotel.negsotelApp.repository.ProvinciaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    public List<ProvinciaEntity> getAll() {
        return provinciaRepository.findAll();
    }

    public ProvinciaEntity getById(Long id) {
        Optional<ProvinciaEntity> provincia = provinciaRepository.findById(id);
        if(provincia.isPresent()){
            return provincia.get();
        } else {
            throw new EntityNotFoundException("No se encontró la provincia con ID " + id);
        }
    }

    public List<CiudadEntity> geByProvinciaId(Long id) {
        return ciudadRepository.findByProvinciaId(id);
    }
}
