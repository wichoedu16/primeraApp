package com.negsotel.negsotelApp.repository;

import com.negsotel.negsotelApp.entity.CiudadEntity;
import com.negsotel.negsotelApp.entity.ProvinciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, Long> {
}
