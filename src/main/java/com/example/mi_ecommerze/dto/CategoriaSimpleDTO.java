package com.example.mi_ecommerze.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaSimpleDTO {

    private Long id;
    private String nombre;
}
