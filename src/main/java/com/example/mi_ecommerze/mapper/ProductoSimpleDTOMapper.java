package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.ProductoSimpleDTO;
import com.example.mi_ecommerze.entity.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoSimpleDTOMapper {

    private final CategoriaSimpleDTOMapper simpleDTOMapper;

    @Autowired
    public ProductoSimpleDTOMapper(CategoriaSimpleDTOMapper simpleDTOMapper){
        this.simpleDTOMapper = simpleDTOMapper;
    }

    public ProductoSimpleDTO toSimpleDTO(Producto producto){
        if(producto == null){
            return null;
        }
        return ProductoSimpleDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .categoria(simpleDTOMapper.toSimpleDTO(producto.getCategoria()))
                .build();
    }
}
