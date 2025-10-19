package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.CategoriaSimpleDTO;
import com.example.mi_ecommerze.entity.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CategoriaSimpleDTOMapper {

    public CategoriaSimpleDTO toSimpleDTO(Categoria categoria){
        if(categoria == null){
            return null;
        }

        return CategoriaSimpleDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build();
    }
}
