package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.entity.EmpleadoEntity;
import com.negsotel.negsotelApp.repository.EmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private CargoService cargoService;
    @Autowired
    private CiudadService ciudadService;
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

    public EmpleadoEntity createEmpleado(EmpleadoEntity empleado) {
        CargoEntity cargoEncontrado = validarCargo(empleado.getCargoId());
        CiudadEntity ciudadEncontrada = validarCiudad(empleado.getCiudadId());
        empleado.setCargo(cargoEncontrado);
        empleado.setCiudad(ciudadEncontrada);
        empleado.setSalario(cargoEncontrado.getSalario());
        empleado.setFechaCreacion(LocalDateTime.now());
        return empleadoRepository.save(empleado);
    }

    private CargoEntity validarCargo(Long cargoId) {
        return cargoService.getById(cargoId);
    }

    private CiudadEntity validarCiudad(Long ciudadId) {
        return ciudadService.getById(ciudadId);
    }

    public EmpleadoEntity updateEmpleado(Long id, EmpleadoEntity empleado) {
        return empleado;
    }

    public void deleteById(Long id) {
        Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()){
            empleadoRepository.delete(empleado.get());
        }else {
            throw new EntityNotFoundException("No se encontró el empleado con ID " + id);
        }
    }

}
