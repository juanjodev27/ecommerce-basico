package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.DetalleCarritoSimpleDTO;
import com.example.mi_ecommerze.entity.DetalleCarrito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetalleCarritoMapper {

    private final ProductoSimpleDTOMapper productoSimpleDTOMapper;

    public DetalleCarritoSimpleDTO toSimpleDTO(DetalleCarrito detalleCarrito){
        if(detalleCarrito == null){
            return null;
        }

        return DetalleCarritoSimpleDTO.builder()
                .id(detalleCarrito.getId())
                .cantidad(detalleCarrito.getCantidad())
                .precioUnitario(detalleCarrito.getPrecioUnitario())
                .productos(productoSimpleDTOMapper.toSimpleDTO(detalleCarrito.getProducto()))
                .build();
    }

}
