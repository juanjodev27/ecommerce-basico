package com.example.mi_ecommerze.service;

import com.example.mi_ecommerze.entity.Carrito;
import com.example.mi_ecommerze.entity.DetalleCarrito;
import com.example.mi_ecommerze.entity.Producto;
import com.example.mi_ecommerze.entity.Usuario;
import com.example.mi_ecommerze.repository.CarritoRepository;
import com.example.mi_ecommerze.repository.DetalleCarritoRepository;
import com.example.mi_ecommerze.repository.ProductoRepository;
import com.example.mi_ecommerze.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    public void agregarProductoAlCarrito(Long usuarioId, Long productoId, int cantidad){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Carrito carrito = carritoRepository.findByUsuario(usuario)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);
                    return carritoRepository.save(nuevoCarrito);
                });

        DetalleCarrito detalle = detalleCarritoRepository.findByCarritoAndProducto(carrito,producto)
                .orElseGet(() -> {
                    DetalleCarrito nuevoDetalle = new DetalleCarrito();
                    nuevoDetalle.setCarrito(carrito);
                    nuevoDetalle.setProducto(producto);
                    nuevoDetalle.setCantidad(0);
                    nuevoDetalle.setPrecioUnitario(producto.getPrecio());
                    return nuevoDetalle;
                });

        detalle.setCantidad(detalle.getCantidad() + cantidad);
        detalleCarritoRepository.save(detalle);
    }

    public void eliminarProductoDelCarrito(Long usuarioId, Long productoId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        DetalleCarrito detalle = detalleCarritoRepository.findByCarritoAndProducto(carrito,producto)
                .orElseThrow(() -> new RuntimeException("El producto no esta en el carrito"));

        detalleCarritoRepository.save(detalle);
    }

    public Carrito obtenerCarritoPorUsuario(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return carritoRepository.findByUsuario(usuario)
                .orElseThrow(()-> new RuntimeException("El usuario no tiene Carrito"));
    }

    public void vaciarCarrito(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        detalleCarritoRepository.deleteAll(carrito.getDetalleCarritos());
    }
}
