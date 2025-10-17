package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.ProductoDTO;
import com.example.mi_ecommerze.entity.Categoria;
import com.example.mi_ecommerze.entity.Producto;
import com.example.mi_ecommerze.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoMapper {


    private final CategoriaRepository categoriaRepository;

    public Producto toEntity(ProductoDTO dto){
        if(dto == null){
            return null;
        }

        Categoria categoria = null;
        if(dto.getCategoriaId() != null){
            categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        }
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .cantidad(dto.getCantidad())
                .categoria(categoria)
                .build();
    }

    public ProductoDTO toDTO(Producto producto){
        if (producto == null){
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setCantidad(producto.getCantidad());

        if (producto.getCategoria() != null){
            dto.setCategoriaId(producto.getCategoria().getId());
            dto.setCategoriaNombre(producto.getCategoria().getNombre());
        }
        return dto;
    }
}
