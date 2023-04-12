package com.negsotel.negsotelApp.repository;

import com.negsotel.negsotelApp.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "cargos")
public interface CargoRepository extends JpaRepository<CargoEntity, Long> {
}
