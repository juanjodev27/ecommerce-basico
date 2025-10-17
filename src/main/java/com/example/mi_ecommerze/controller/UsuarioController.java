package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.dto.UsuarioDTO;
import com.example.mi_ecommerze.entity.Usuario;
import com.example.mi_ecommerze.mapper.UsuarioMapper;
import com.example.mi_ecommerze.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        List<UsuarioDTO> usuariosDTO = usuarioService.listarUsuarios()
                .stream()
                .map(UsuarioMapper::toDto)
                .toList();
        return ResponseEntity.ok(usuariosDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioService.obtenerPorId(id));
        return usuarioOptional
                .map(usuario -> ResponseEntity.ok(UsuarioMapper.toDto(usuario)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> guardarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
         Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
         Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(usuarioGuardado));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioPorId(@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id){
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioService.actualizarUsuario(usuario,id));
       return optionalUsuario
               .map(u -> ResponseEntity.ok(UsuarioMapper.toDto(u)))
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
