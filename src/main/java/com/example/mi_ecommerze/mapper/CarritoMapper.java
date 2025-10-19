package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.CarritoDTO;
import com.example.mi_ecommerze.dto.DetalleCarritoSimpleDTO;
import com.example.mi_ecommerze.entity.Carrito;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarritoMapper {

    private final DetalleCarritoMapper detalleCarritoMapper;

    public CarritoDTO toDTO(Carrito carrito){
        if(carrito == null){
            return null;
        }

        List<DetalleCarritoSimpleDTO> detallesDTO = carrito.getDetalleCarritos()
                .stream()
                .map(detalleCarritoMapper::toSimpleDTO)
                .toList();
        return CarritoDTO.builder()
                .id(carrito.getId())
                .detalleCarrito(detallesDTO)
                .build();
    }
}
