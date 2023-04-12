package com.negsotel.negsotelApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="cargo")
public class CargoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo-sequence")
    private Long id;
    @NonNull
    private String descripcion;
    @Column(unique = true, nullable = false)
    private String codigo;
    @NonNull
    private Double salario;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    @ManyToOne
    @JoinColumn(name = "departamentoId")
    private DepartamentoEntity departamento;
    @OneToMany(mappedBy = "cargo")
    private List<EmpleadoEntity> empleados;
}
