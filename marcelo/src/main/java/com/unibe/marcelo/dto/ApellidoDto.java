package com.unibe.marcelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApellidoDto {
    private Long id;
    private String apellido;
    private Float salario;
    private Boolean activo;
}
