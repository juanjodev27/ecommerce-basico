package com.example.mi_ecommerze.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetalleCarritoSimpleDTO {

    private Long id;
    private int cantidad;
    private BigDecimal precioUnitario;
    private  ProductoSimpleDTO productos;
}
