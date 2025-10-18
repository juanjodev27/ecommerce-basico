package com.example.mi_ecommerze.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

}
