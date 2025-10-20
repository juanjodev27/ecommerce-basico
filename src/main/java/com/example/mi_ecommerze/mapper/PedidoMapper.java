package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.DetallesPedidosSimpleDTO;
import com.example.mi_ecommerze.dto.PedidoDTO;
import com.example.mi_ecommerze.entity.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final DetallePedidoMapper detallePedidoMapper;

    public PedidoDTO toSimpleDTO(Pedido pedido){
        if(pedido == null){
            return null;
        }

        List<DetallesPedidosSimpleDTO> detalles = pedido.getDetalleDePedidos() != null
                ? pedido.getDetalleDePedidos().stream()
                .map(detallePedidoMapper::toSimpleDTO)
                .toList() : List.of();

        return PedidoDTO.builder()
                .id(pedido.getId())
                .usuarioNombre(pedido.getUsuario() != null ? pedido.getUsuario().getNombre() : null)
                .direccionEnvio(pedido.getDireccionEnvio())
                .precioTotal(pedido.getPrecioTotal())
                .estado(pedido.getEstado().toString())
                .fechaCreacion(pedido.getFechaCreacion())
                .detallesPedidos(detalles)
                .build();
    }
}
