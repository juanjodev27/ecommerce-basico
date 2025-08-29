package com.example.mi_ecommerze.service;

import com.example.mi_ecommerze.entity.*;
import com.example.mi_ecommerze.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private DetalleDePedidosRepository detalleDePedidosRepository;

    @Transactional
    public Pedido crearPedido(Long usuarioId,String direccionEnvio){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("El usuario no posee un carrito"));

        if (carrito.getDetalleCarritos().isEmpty()){
            throw new RuntimeException("El carrito esta vacio");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccionEnvio(direccionEnvio);
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setPrecioTotal(BigDecimal.ZERO);

        List<DetalleDePedidos> detalleDePedidos = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(DetalleCarrito dc: carrito.getDetalleCarritos()){
            DetalleDePedidos dp = new DetalleDePedidos();
            dp.setPedido(pedido);
            dp.setProducto(dc.getProducto());
            dp.setCantidad(dc.getCantidad());
            dp.setNombreProducto(dc.getProducto().getNombre());
            dp.setPrecioUnitario(dc.getProducto().getPrecio());

            BigDecimal subtotal = dc.getProducto().getPrecio().multiply(BigDecimal.valueOf(dc.getCantidad()));
            total = total.add(subtotal);
            detalleDePedidos.add(dp);
        }

        pedido.setPrecioTotal(total);
        pedido.setDetalleDePedidos(detalleDePedidos);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        detalleCarritoRepository.deleteAll(carrito.getDetalleCarritos());

        return pedidoGuardado;
    }

    public List<Pedido> obtenerPedidoPorUsuario(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return pedidoRepository.findByUsuario(usuario);
    }

    public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Transactional
    public Pedido actualizarEstadoPedido(Long pedidoId, Pedido.EstadoPedido nuevoEstado){
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID " + pedidoId));

        Pedido.EstadoPedido estadoActual = pedido.getEstado();
        validarTransicionDeEstado(estadoActual, nuevoEstado);
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }
    private void validarTransicionDeEstado(Pedido.EstadoPedido estadoActual, Pedido.EstadoPedido nuevoEstado) {
        switch (estadoActual) {
            case PENDIENTE:
                if (nuevoEstado != Pedido.EstadoPedido.CONFIRMADO && nuevoEstado != Pedido.EstadoPedido.CANCELADO) {
                    throw new IllegalStateException("Transición de PENDIENTE a " + nuevoEstado + " no permitida.");
                }
                break;
            case CONFIRMADO:
                if (nuevoEstado != Pedido.EstadoPedido.PROCESANDO && nuevoEstado != Pedido.EstadoPedido.CANCELADO) {
                    throw new IllegalStateException("Transición de CONFIRMADO a " + nuevoEstado + " no permitida.");
                }
                break;
            case PROCESANDO:
                if (nuevoEstado != Pedido.EstadoPedido.ENVIADO) {
                    throw new IllegalStateException("Transición de PROCESANDO a " + nuevoEstado + " no permitida.");
                }
                break;
            case ENVIADO:
                if (nuevoEstado != Pedido.EstadoPedido.ENTREGADO && nuevoEstado != Pedido.EstadoPedido.DEVUELTO) {
                    throw new IllegalStateException("Transición de ENVIADO a " + nuevoEstado + " no permitida.");
                }
                break;
            case ENTREGADO:
            case CANCELADO:
            case DEVUELTO:
                throw new IllegalStateException("El pedido ya está en un estado final y no puede ser modificado.");
            default:
                throw new IllegalStateException("Estado de pedido actual desconocido: " + estadoActual);
        }
     }
}
