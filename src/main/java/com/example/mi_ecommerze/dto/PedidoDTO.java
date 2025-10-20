package com.example.mi_ecommerze.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoDTO {

    private Long id;
    private String usuarioNombre;
    private BigDecimal precioTotal;
    private String direccionEnvio;
    private String estado;
    private LocalDateTime fechaCreacion;
    private List<DetallesPedidosSimpleDTO> detallesPedidos;
}
