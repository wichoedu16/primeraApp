package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.entity.ProvinciaEntity;
import com.negsotel.negsotelApp.repository.CiudadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;
    public List<CiudadEntity> getAll() {
        return ciudadRepository.findAll();
    }

    public CiudadEntity getByCodigo(Long ciudadId) {
        CiudadEntity ciudad = ciudadRepository.findByCodigo(ciudadId);
        if (Objects.nonNull(ciudad)) {
            return ciudad;
        } else {
            throw new EntityNotFoundException("No se encontró la ciudad con ID " + ciudadId);
        }
    }
    public CiudadEntity getByCodigoCiudadAndProvincia(Long ciudadId, Long provinciaId) {
        CiudadEntity ciudad = ciudadRepository.findByProvinciaIdAndCodigo(provinciaId, ciudadId);
        if (Objects.nonNull(ciudad)) {
            return ciudad;
        } else {
            throw new EntityNotFoundException("No se encontró la ciudad con ID " + ciudadId);
        }
    }

    public List<CiudadEntity> geByProvinciaId(Long provinciaId) {
        List<CiudadEntity> ciudades = ciudadRepository.findByProvinciaId(provinciaId);
        return ciudades;
    }

    public CiudadEntity getByIds(Long ciudadId, Long provinciaId) {
        CiudadEntity ciudad = ciudadRepository.findByProvinciaIdAndCodigo(provinciaId, ciudadId);
        return ciudad;
    }
}
