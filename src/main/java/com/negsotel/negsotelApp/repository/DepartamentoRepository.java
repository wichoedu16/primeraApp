package com.negsotel.negsotelApp.repository;

import com.negsotel.negsotelApp.entity.DepartamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {
    Page<DepartamentoEntity> findAll(Pageable pageable);

    DepartamentoEntity findByCodigo(String codigo);
}
