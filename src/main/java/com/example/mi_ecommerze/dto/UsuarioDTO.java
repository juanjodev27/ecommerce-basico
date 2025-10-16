package com.example.mi_ecommerze.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 500, message = "La dirección debe tener hasta 500 caracteres")
    private String direccion;

    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    private int edad;
}
