package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.EmpleadoEntity;
import com.negsotel.negsotelApp.repository.EmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private CargoService cargoService;
    public List<EmpleadoEntity> findAll() {
        return empleadoRepository.findAll();
    }

    public EmpleadoEntity getById(Long id) {
        Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            return empleado.get();
        } else {
            throw new EntityNotFoundException("No se encontró el empleado con ID " + id);
        }
    }
}
