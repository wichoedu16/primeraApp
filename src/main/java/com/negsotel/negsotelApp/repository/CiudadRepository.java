package com.negsotel.negsotelApp.repository;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {
    List<CiudadEntity> findByProvinciaId(Long idProvincia);
    CiudadEntity findByCodigo(Long codigoCiudad);
    CiudadEntity findByProvinciaIdAndCodigo(Long provinciaId, Long ciudadId);
}
