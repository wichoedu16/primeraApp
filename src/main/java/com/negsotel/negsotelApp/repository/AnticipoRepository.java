package com.negsotel.negsotelApp.repository;

import com.negsotel.negsotelApp.entity.AnticipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnticipoRepository extends JpaRepository<AnticipoEntity, Long> {
    List<AnticipoEntity> findByEmpleadoId(Long idEmpleado);
}
