package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.AnticipoEntity;
import com.negsotel.negsotelApp.entity.EmpleadoEntity;
import com.negsotel.negsotelApp.repository.AnticipoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnticipoService {
    @Autowired
    private AnticipoRepository anticipoRepository;
    @Autowired
    private EmpleadoService empleadoService;
    public List<AnticipoEntity> findAll() {
        return anticipoRepository.findAll();
    }


    public AnticipoEntity getById(Long id) {
        Optional<AnticipoEntity> anticipo = anticipoRepository.findById(id);
        if (anticipo.isPresent()) {
            return anticipo.get();
        } else {
            throw new EntityNotFoundException("No se encontró el anticipo con ID " + id);
        }
    }

    public List<AnticipoEntity> getAllByEmpleadoId(Long idEmpleado) {
        return anticipoRepository.findByEmpleadoId(idEmpleado);
    }

    public AnticipoEntity createCargo(AnticipoEntity anticipo) {
        Optional<EmpleadoEntity> empleado = Optional.ofNullable(empleadoService.getById(anticipo.getEmpleadoId()));
        if (empleado.isPresent()){
            validarMontoPedido(anticipo.getMonto(), anticipo.getEmpleadoId());
            anticipo.setEmpleado(anticipo.getEmpleado());
            anticipo.setFechaCreacion(LocalDateTime.now());
            return anticipoRepository.save(anticipo);
        }else {
            throw new EntityNotFoundException("No se encontró el empleado con ID"+anticipo.getEmpleadoId());
        }
    }

    private void validarMontoPedido(Double monto, Long idEmpleado) {
        EmpleadoEntity empleado = empleadoService.getById(idEmpleado);
        if(monto > empleado.getSalario()){
            throw new EntityExistsException("El monto solicitado es mayor al sueldo");
        }
    }
}
