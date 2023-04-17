package com.negsotel.negsotelApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="empleado")
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "empleado-sequence")
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
    private LocalDateTime fechaNacimiento;

    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    @Column(name = "nacionalidad", length = 15, nullable = false)
    private String nacionalidad;

    @Column(name = "estado_civil", nullable = false)
    private String estadoCivil;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_reingreso")
    private LocalDateTime fechaReingreso;

    @Column(name = "id_cargo", nullable = false)
    private Long idCargo;

    @Column(name = "grado_academico")
    private String gradoAcademico;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "sector", nullable = false)
    private String sector;

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

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "cargo_id", insertable = false, updatable = false)
    private CargoEntity cargo;

}
