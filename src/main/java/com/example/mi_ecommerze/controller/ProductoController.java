package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.dto.ProductoDTO;
import com.example.mi_ecommerze.dto.ProductoSimpleDTO;
import com.example.mi_ecommerze.entity.Producto;
import com.example.mi_ecommerze.mapper.ProductoMapper;
import com.example.mi_ecommerze.mapper.ProductoSimpleDTOMapper;
import com.example.mi_ecommerze.service.ProductoService;
import jakarta.persistence.PrePersist;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    private final ProductoMapper productoMapper;

    private final ProductoSimpleDTOMapper simpleDTOMapper;

    @GetMapping
    public ResponseEntity<List<ProductoSimpleDTO>> listarProductos(){
        List<ProductoSimpleDTO> productoDTOS = productoService.listarProductos()
                .stream()
                .map(simpleDTOMapper::toSimpleDTO)
                .toList();
        return ResponseEntity.ok(productoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoSimpleDTO> obtenerProductoPorId(@PathVariable Long id){
        Optional<Producto> productOptional = Optional.ofNullable(productoService.obtenerProductoPorId(id));
        return productOptional
                .map(producto -> ResponseEntity.ok(simpleDTOMapper.toSimpleDTO(producto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> guardarProducto(@Valid @RequestBody ProductoDTO productoDTO){
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto productoGuardado = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoMapper.toDTO(productoGuardado));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoDTO> actualizarProductoPorId(@Valid @RequestBody ProductoDTO productoDTO, @PathVariable Long id){
        Producto producto = productoMapper.toEntity(productoDTO);
        Optional<Producto> productoOptional = Optional.ofNullable(productoService.actualizarProducto(producto,id));
        return productoOptional
                .map(p -> ResponseEntity.ok(productoMapper.toDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoSimpleDTO>> buscarProductoPorNombre(@RequestParam String nombre){
        List<ProductoSimpleDTO> productos = productoService.buscarPorNombre(nombre)
                .stream()
                .map(simpleDTOMapper::toSimpleDTO)
                .toList();
        return ResponseEntity.ok(productos);

    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoSimpleDTO>> buscarProductoPorCategoria(@PathVariable Long categoriaId){
        List<ProductoSimpleDTO> productos = productoService.buscarPorCategoria(categoriaId)
                .stream()
                .map(simpleDTOMapper::toSimpleDTO)
                .toList();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoSimpleDTO>> filtrarPorRangoDePrecio(@RequestParam BigDecimal min,
                                                                  @RequestParam BigDecimal max){
        List<ProductoSimpleDTO> productosDTO= productoService.filtrarPorRangoDePrecio(min,max)
                .stream()
                .map(simpleDTOMapper::toSimpleDTO)
                .toList();
        return ResponseEntity.ok(productosDTO);
    }

    @PutMapping("/{productoId}/disminuir-stock")
    public ResponseEntity<Void> disminuirStock(@PathVariable Long productoId,
                                               @RequestParam int cantidad){
        productoService.disminuirStock(productoId,cantidad);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productoId}/aumentar-stock")
    public ResponseEntity<Void> aumentarStock(@PathVariable Long productoId,
                                              @RequestParam int cantidad){
        productoService.aumentarStock(productoId,cantidad);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productoId}/hay-stock")
    public ResponseEntity<Boolean> hayStockDisponible(@PathVariable Long productoId,
                                                      @RequestParam int cantidad){
        Boolean disponible = productoService.hayStockDisponible(productoId,cantidad);
        return ResponseEntity.ok(disponible);
    }
}
