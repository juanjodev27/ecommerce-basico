package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.DetallesPedidosSimpleDTO;
import com.example.mi_ecommerze.entity.DetalleDePedidos;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    public DetallesPedidosSimpleDTO toSimpleDTO(DetalleDePedidos pedidos){
        if(pedidos == null){
            return null;
        }

        return DetallesPedidosSimpleDTO.builder()
                .nombreProducto(pedidos.getNombreProducto())
                .cantidad(pedidos.getCantidad())
                .precioUnitario(pedidos.getPrecioUnitario())
                .build();
    }
}
