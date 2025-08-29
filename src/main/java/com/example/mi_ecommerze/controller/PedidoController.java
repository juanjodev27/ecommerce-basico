package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.entity.Pedido;
import com.example.mi_ecommerze.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/{usuarioId}")
        public ResponseEntity<Pedido> crearPedido(@PathVariable Long usuarioId,
                                                  @RequestParam String direccionEnvio){
        Pedido pedidoGuardado = pedidoService.crearPedido(usuarioId,direccionEnvio);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);
    }

   @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> obtenerPedidoPorUsuario(@PathVariable Long usuarioId){
       List<Pedido> pedidos = pedidoService.obtenerPedidoPorUsuario(usuarioId);
       return ResponseEntity.ok(pedidos);
   }

   @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long pedidoId){
        Pedido pedido = pedidoService.obtenerPedidoPorId(pedidoId);
        return ResponseEntity.ok(pedido);
   }

   @PutMapping("/{pedidoId}/estado")
    public ResponseEntity<Pedido> actualizarPedidoPorId(@PathVariable Long pedidoId,
                                                        @RequestParam Pedido.EstadoPedido nuevoEstado){
        Pedido pedido = pedidoService.actualizarEstadoPedido(pedidoId,nuevoEstado);
        return ResponseEntity.ok(pedido);
   }
}
