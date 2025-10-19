package com.example.mi_ecommerze.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoSimpleDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int cantidad;
    private CategoriaSimpleDTO categoria;
}
