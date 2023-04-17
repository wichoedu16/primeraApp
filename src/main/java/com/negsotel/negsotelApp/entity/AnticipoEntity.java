package com.negsotel.negsotelApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="anticipo")
public class AnticipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "anticipo-sequence")
    private Long id;
    private @Column(nullable = false)
    String motivo;
    private @Column(nullable = false)
    String estado;
    @Column(unique = true, nullable = false)
    private Double monto;
    private LocalDateTime fechaPedido;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private EmpleadoEntity empleado;
    @Column(name = "empleado_id", insertable = false, updatable = false)
    private Long empleadoId;
}
