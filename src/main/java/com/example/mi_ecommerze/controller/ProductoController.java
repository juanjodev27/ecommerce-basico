package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.entity.Producto;
import com.example.mi_ecommerze.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> actualizarProductoPorId(@PathVariable Long id){
        Optional<Producto> producto = Optional.ofNullable(productoService.obtenerProductoPorId(id));
        return producto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
        Producto productoGuardado = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProductoPorId(@RequestBody Producto producto, @PathVariable Long id){
        Optional<Producto> productoActualizado = Optional.ofNullable(productoService.actualizarProducto(producto, id));
        return productoActualizado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductoPorNombre(@RequestParam String nombre){
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);

    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> buscarProductoPorCategoria(@PathVariable Long categoriaId){
        List<Producto> productos = productoService.buscarPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Producto>> filtrarPorRangoDePrecio(@RequestParam BigDecimal min,
                                                                  @RequestParam BigDecimal max){
        List<Producto> productos = productoService.filtrarPorRangoDePrecio(min,max);
        return ResponseEntity.ok(productos);
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
