package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.dto.CategoriaDTO;
import com.example.mi_ecommerze.entity.Categoria;
import com.example.mi_ecommerze.mapper.CategoriaMapper;
import com.example.mi_ecommerze.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategoria(){
        List<CategoriaDTO> categorias = categoriaService.listarCategorias()
                .stream()
                .map(categoriaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaPorId(@PathVariable Long id){
        Optional<Categoria> categoriaOptional = Optional.ofNullable(categoriaService.obtenerCategoriaPorId(id));
        return categoriaOptional
                .map(categoria -> ResponseEntity.ok(categoriaMapper.toDTO(categoria)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<CategoriaDTO> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria categoriaGuardado = categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaMapper.toDTO(categoriaGuardado));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoriaPorId(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id){
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Optional<Categoria> categoriaActualizado = Optional.ofNullable(categoriaService.actualizarCategoria(categoria, id));
        return categoriaActualizado
                .map(categoria1 -> ResponseEntity.ok(categoriaMapper.toDTO(categoria1)))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
