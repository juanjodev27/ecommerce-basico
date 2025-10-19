package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.dto.CarritoDTO;
import com.example.mi_ecommerze.entity.Carrito;
import com.example.mi_ecommerze.mapper.CarritoMapper;
import com.example.mi_ecommerze.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoMapper carritoMapper;

    @GetMapping("/{usuarioId}/agregar/{productoId}")
    public ResponseEntity<Void> agregarProductoAlCarrito(@PathVariable Long usuarioId,
                                                         @PathVariable Long productoId,
                                                         @RequestParam(defaultValue = "1") int cantidad){
        carritoService.agregarProductoAlCarrito(usuarioId,productoId,cantidad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{usuarioId}/eliminar/{productoId}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long usuarioId,
                                                @PathVariable Long productoId){
        carritoService.eliminarProductoDelCarrito(usuarioId,productoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarritoDTO> obtenerCarritoPorId(@PathVariable Long usuarioId){
        Carrito carrito = carritoService.obtenerCarritoPorUsuario(usuarioId);
        CarritoDTO carritoDTO = carritoMapper.toDTO(carrito);
        return ResponseEntity.ok(carritoDTO);
    }

    @DeleteMapping("/{usuarioId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId){
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }

}
