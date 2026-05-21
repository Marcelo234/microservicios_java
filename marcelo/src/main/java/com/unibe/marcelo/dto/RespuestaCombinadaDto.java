package com.unibe.marcelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespuestaCombinadaDto {
    private Long idNombre;
    private String nombre;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private Long idApellido;
    private String apellido;
    private Float salario;
    private Boolean activo;
    private String nombreCompleto;
}
