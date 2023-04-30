package com.negsotel.negsotelApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="ciudad")
public class CiudadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo_ciudad;
    @NonNull
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private ProvinciaEntity provincia;
    @Column(name = "provincia_id", insertable = false, updatable = false)
    private Long provinciaId;
}
