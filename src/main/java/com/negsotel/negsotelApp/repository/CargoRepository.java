package com.negsotel.negsotelApp.repository;

import com.negsotel.negsotelApp.entity.CargoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Long> {
    List<CargoEntity> findByDepartamentoId(Long idDepartamento);

    Page<CargoEntity> findAll(Pageable pageable);

    CargoEntity findByCodigo(String codigo);
}
