package com.negsotel.negsotelApp.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnticipoDTO {

    LocalDate fechaPedido;
    LocalDate fechaEntrega;
    String empleado;
    Double sueldo;
    String estado;
    Double disponible;
}
