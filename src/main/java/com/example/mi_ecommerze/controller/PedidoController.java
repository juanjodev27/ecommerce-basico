package com.example.mi_ecommerze.controller;

import com.example.mi_ecommerze.dto.PedidoDTO;
import com.example.mi_ecommerze.entity.Pedido;
import com.example.mi_ecommerze.mapper.PedidoMapper;
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

    @Autowired
    private PedidoMapper pedidoMapper;

    @PostMapping("/{usuarioId}")
        public ResponseEntity<PedidoDTO> crearPedido(@PathVariable Long usuarioId,
                                                     @RequestParam String direccionEnvio){
        Pedido pedidoGuardado = pedidoService.crearPedido(usuarioId,direccionEnvio);
        PedidoDTO pedidoDTO = pedidoMapper.toSimpleDTO(pedidoGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
    }

   @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidoPorUsuario(@PathVariable Long usuarioId){
       List<Pedido> pedidos = pedidoService.obtenerPedidoPorUsuario(usuarioId);
       List<PedidoDTO> pedidoDTOS = pedidos.stream()
               .map(pedidoMapper::toSimpleDTO)
               .toList();
       return ResponseEntity.ok(pedidoDTOS);
   }

   @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long pedidoId){
        Pedido pedido = pedidoService.obtenerPedidoPorId(pedidoId);
        PedidoDTO pedidoDTO = pedidoMapper.toSimpleDTO(pedido);
        return ResponseEntity.ok(pedidoDTO);
   }

   @PutMapping("/{pedidoId}/estado")
    public ResponseEntity<PedidoDTO> actualizarPedidoPorId(@PathVariable Long pedidoId,
                                                        @RequestParam Pedido.EstadoPedido nuevoEstado){
        Pedido pedido = pedidoService.actualizarEstadoPedido(pedidoId,nuevoEstado);
        PedidoDTO pedidoDTO = pedidoMapper.toSimpleDTO(pedido);
        return ResponseEntity.ok(pedidoDTO);
   }
}
