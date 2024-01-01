package com.negsotel.negsotelApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="empleado")
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado-sequence")
    private Long id;

    @Column(name = "cedula", nullable = false, unique = true)
    String cedula;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "estado", length = 1, nullable = false)
    private String estado;

    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", length = 60, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 60, nullable = false)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    @Column(name = "nacionalidad", length = 15, nullable = false)
    private String nacionalidad;

    @Column(name = "estado_civil", nullable = false)
    private String estadoCivil;

    @Column(name = "fecha_ingreso", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_salida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;

    @Column(name = "fecha_reingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaReingreso;

    @Column(name = "grado_academico")
    private String gradoAcademico;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "celular", length = 10)
    private String celular;

    @Column(name = "calle_principal", nullable = false)
    private String callePrincipal;

    @Column(name = "calle_secundaria")
    private String calleSecundaria;

    @Column(name = "correo_personal")
    private String correoPersonal;

    @Column(name = "correo_institucional")
    private String correoInstitucional;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private CargoEntity cargo;
    @Column(name = "cargo_id", insertable = false, updatable = false)
    private Long cargoId;
}
