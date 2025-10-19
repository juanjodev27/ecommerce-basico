package com.example.mi_ecommerze.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarritoDTO {

    private Long id;
    private List<DetalleCarritoSimpleDTO> detalleCarrito;
}
