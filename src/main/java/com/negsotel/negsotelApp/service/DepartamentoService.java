package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.entity.DepartamentoEntity;
import com.negsotel.negsotelApp.repository.DepartamentoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    private CargoService cargoService;

    public List<DepartamentoEntity> getAll() {
        return departamentoRepository.findAll();
    }

    public DepartamentoEntity getById(Long id) {
        Optional<DepartamentoEntity> departamento = departamentoRepository.findById(id);
        if(departamento.isPresent()){
            return departamento.get();
        } else {
            throw new EntityNotFoundException("No se encontró el departamento con ID " + id);
        }
    }

    public Optional<DepartamentoEntity> getDepartamentoById(Long id) {
        Optional<DepartamentoEntity> departamento = departamentoRepository.findById(id);
        if(departamento.isPresent()){
            return departamento;
        } else {
            throw new EntityNotFoundException("No se encontró el departamento con ID " + id);
        }
    }
    public DepartamentoEntity createDepartamento(DepartamentoEntity departamento) {
        validarCodigoDepartamento(departamento.getCodigo());
        departamento.setFechaCreacion(LocalDateTime.now());
        return departamentoRepository.save(departamento);
    }

    public DepartamentoEntity updateDepartamento(Long id, DepartamentoEntity departamento) {
        Optional<DepartamentoEntity> departamentoEncontrado = getDepartamentoById(id);
        if (departamentoEncontrado.isPresent()) {
            departamentoEncontrado.get().setFechaModifica(LocalDateTime.now());
            departamentoEncontrado.get().setDescripcion(departamento.getDescripcion());
            departamentoEncontrado.get().setCodigo(departamento.getCodigo());
            return departamentoRepository.save(departamentoEncontrado.get());
        } else {
            throw new EntityNotFoundException("No se encontró el departamento con ID " + departamento.getId());
        }
    }

    public void deleteDepartamento(Long id) {
        Optional<DepartamentoEntity> departamentoEncontrado = getDepartamentoById(id);
        if (departamentoEncontrado.isPresent()){
            departamentoRepository.delete(departamentoEncontrado.get());
        } else {
            throw new EntityNotFoundException("No se encontró el departamento con ID " + id);
        }
    }

    private void validarCodigoDepartamento(String codigo) {
        DepartamentoEntity departamento = departamentoRepository.findByCodigo(codigo);
        if (Objects.nonNull(departamento)){
            throw new EntityExistsException("El cargo ya existe para el código: " + departamento.getDescripcion());
        }
    }

    public List<CargoEntity> getCargosByDepartamento(Long id) {
        return cargoService.geByDepartamentoId(id);
    }
}
