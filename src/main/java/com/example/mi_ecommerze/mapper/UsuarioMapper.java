package com.example.mi_ecommerze.mapper;

import com.example.mi_ecommerze.dto.UsuarioDTO;
import com.example.mi_ecommerze.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto){
        return Usuario.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .direccion(dto.getDireccion())
                .edad(dto.getEdad())
                .build();
    }

    public static UsuarioDTO toDto(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setDireccion(usuario.getDireccion());
        dto.setEdad(usuario.getEdad());
        return dto;
    }
}
