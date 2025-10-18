package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.CategoriaDTO;
import com.example.mi_ecommerze.entity.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriaMapper {

    public Categoria toEntity(CategoriaDTO dto){
        if(dto == null){
            return  null;
        }

        return Categoria.builder()
                .nombre(dto.getNombre())
                .build();
    }

    public CategoriaDTO toDTO(Categoria categoria){
        if (categoria == null){
            return  null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }
}
