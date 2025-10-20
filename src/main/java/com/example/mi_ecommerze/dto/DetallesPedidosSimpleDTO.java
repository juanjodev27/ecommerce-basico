package com.example.mi_ecommerze.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetallesPedidosSimpleDTO {

    private String nombreProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
}
