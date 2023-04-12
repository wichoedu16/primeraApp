package com.negsotel.negsotelApp.service;

import com.negsotel.negsotelApp.entity.CargoEntity;
import com.negsotel.negsotelApp.repository.CargoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CargoService {
    @Autowired
    CargoRepository cargoRepository;

    CargoEntity cargoEntity;
    public List<CargoEntity> findAll() {
        List<CargoEntity> cargos = cargoRepository.findAll();
        if (Objects.isNull(cargos)) {
            cargos = new ArrayList<>();
        }
        return cargos;
    }

    public CargoEntity findById(Long id) {
        CargoEntity cargoEntity = findById( id);
        if (Objects.isNull(cargoEntity)) {
            throw new EntityNotFoundException("No se encuentra la entidad con id: " + id);
        }
        return cargoEntity;
    }

    public CargoEntity save(CargoEntity cargo) {
        return cargoRepository.save(cargo);
    }

    public CargoEntity update(Long id, CargoEntity cargo) {
        CargoEntity cargoEntity = findById(id);
        if (Objects.isNull(cargoEntity)) {
            throw new EntityNotFoundException("No existe informacion a editar");
        }
        cargoEntity.setCodigo(cargo.getCodigo());
        cargoEntity.setDescripcion(cargo.getDescripcion());
        cargoEntity.setSalario(cargo.getSalario());
        return cargoEntity;
    }

    public void deleteById(Long id) {
        CargoEntity cargo = findById(id);
        cargoRepository.delete(cargo);
    }
}
