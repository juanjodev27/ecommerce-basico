package com.example.mi_ecommerze.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacía")
    @Size(max = 500, message = "La descripcion debe tener hasta 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.01", message = "El precio debe de ser mayor a cero.")
    private BigDecimal precio;

    @Min(value = 0, message ="La cantidad debe ser mayor a cero.")
    private int cantidad;

    @NotNull(message = "La categoria es obligatoria.")
    private Long categoriaId;

    private String categoriaNombre;
}
