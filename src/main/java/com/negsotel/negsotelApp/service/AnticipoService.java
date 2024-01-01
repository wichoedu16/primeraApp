package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.controller.dto.AnticipoDTO;
import com.negsotel.negsotelApp.entity.AnticipoEntity;
import com.negsotel.negsotelApp.entity.EmpleadoEntity;
import com.negsotel.negsotelApp.repository.AnticipoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<AnticipoDTO> getAllByEmpleadoId(Long idEmpleado) {
        List<AnticipoDTO> reportes = new ArrayList<>();
        EmpleadoEntity empleado = empleadoService.getById(idEmpleado);
        if(Objects.nonNull(empleado)){
            List<AnticipoEntity> anticipos = anticipoRepository.findByEmpleadoId(empleado.getId());
            if (anticipos.size() > 0){
                for (AnticipoEntity anticipo : anticipos) {
                    AnticipoDTO reporte = new AnticipoDTO();
                    reporte.setEmpleado(empleado.getNombre().concat(" ").concat(empleado.getApellidoPaterno()));
                    reporte.setSueldo(empleado.getSalario());
                    reporte.setFechaPedido(anticipo.getFechaPedido());
                    reporte.setFechaEntrega(anticipo.getFechaEntrega());
                    reporte.setEstado(anticipo.getEstado());
                    reportes.add(reporte);
                    }
                }
            }
        return reportes;
    }

    public AnticipoEntity create(AnticipoEntity anticipo) {
        Optional<EmpleadoEntity> empleado = Optional.ofNullable(empleadoService.getById(anticipo.getEmpleadoId()));
        if (empleado.isPresent()){
            validarMontoPedido(anticipo.getMonto(), anticipo.getEmpleadoId());
            anticipo.setEmpleado(empleado.get());
            anticipo.setEmpleadoId(empleado.get().getId());
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
