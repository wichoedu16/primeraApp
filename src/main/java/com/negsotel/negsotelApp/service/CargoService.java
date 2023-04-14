package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.entity.DepartamentoEntity;
import com.negsotel.negsotelApp.repository.CargoRepository;
import com.negsotel.negsotelApp.repository.DepartamentoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CargoService  {
    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private DepartamentoService departamentoService;

    public Page<CargoEntity> getAll(Pageable pageable) {
        return cargoRepository.findAll(pageable);
    }

    public CargoEntity getById(Long id) {
        Optional<CargoEntity> cargo = cargoRepository.findById(id);
        if (cargo.isPresent()) {
            return cargo.get();
        } else {
            throw new EntityNotFoundException("No se encontró el cargo con ID " + id);
        }
    }

    public List<CargoEntity> geByDepartamentoId(Long idDepartamento) {
        return cargoRepository.findByDepartamentoId(idDepartamento);
    }

    public CargoEntity createCargo(CargoEntity cargo) {
        Optional<DepartamentoEntity> departamento = departamentoService.getDepartamentoById(cargo.getDepartamentoId());
        if (departamento.isPresent()){
            validarCodigoCargo(cargo.getCodigo());
            cargo.setDepartamento(departamento.get());
            cargo.setFechaCreacion(LocalDateTime.now());
            return cargoRepository.save(cargo);
        }else {
            throw new EntityNotFoundException("No se encontró el departamento con ID " + cargo.getDepartamentoId());
        }
    }

    public CargoEntity updateCargo(Long id, CargoEntity cargo) {
        Optional<CargoEntity> cargoEncontrado = cargoRepository.findById(id);
        if (cargoEncontrado.isPresent()){
            cargoEncontrado.get().setDescripcion(cargo.getDescripcion());
            cargoEncontrado.get().setCodigo(cargo.getCodigo());
            cargoEncontrado.get().setSalario(cargo.getSalario());
            cargoEncontrado.get().setDepartamentoId(cargo.getDepartamentoId());
            return cargoRepository.save(cargoEncontrado.get());
        }
        else {
            throw new EntityNotFoundException("No se encontró el cargo con ID " + cargo.getId());
        }
    }

    public void deleteCargo(Long id) {
        Optional<CargoEntity> cargoEncontrado = cargoRepository.findById(id);
        if (cargoEncontrado.isPresent()){
            cargoRepository.delete(cargoEncontrado.get());
        }else {
            throw new EntityNotFoundException("No se encontró el cargo con ID " + id);
        }

    }

    private void validarCodigoCargo(String codigo) {
        CargoEntity cargo = cargoRepository.findByCodigo(codigo);
        if (Objects.nonNull(cargo)){
            throw new EntityExistsException("El cargo ya existe para el código: " + cargo.getDescripcion());
        }
    }
}