package com.negsotel.negsotelApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="departamento")
public class DepartamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "departamento-sequence")
    private Long id;
    @NonNull
    private String descripcion;
    @Column(unique = true, nullable = false)
    private String codigo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;

}
