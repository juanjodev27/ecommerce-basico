package com.example.mi_ecommerze.service;

import com.example.mi_ecommerze.entity.Producto;
import com.example.mi_ecommerze.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Producto producto, Long id){
        Producto productoActualizado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));

        productoActualizado.setNombre(producto.getNombre());
        productoActualizado.setDescripcion(producto.getDescripcion());
        productoActualizado.setPrecio(producto.getPrecio());
        productoActualizado.setCantidad(producto.getCantidad());
        return productoRepository.save(productoActualizado);
    }

    public void eliminarProducto(Long id){
        if(!productoRepository.existsById(id)){
            throw new RuntimeException("Producto no encontrado con id " + id);
        }
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(Long categoriaId){
        return productoRepository.findByCategoriaId(categoriaId);
    }

    public List<Producto> filtrarPorRangoDePrecio(BigDecimal min, BigDecimal max){
        return productoRepository.findByPrecioBetween(min,max);
    }

    public void disminuirStock(Long productoId, int cantidad){
        Producto producto = obtenerProductoPorId(productoId);
        if ((producto.getCantidad() < cantidad)){
            throw new RuntimeException("Stock insuficiente para el producto " + producto.getNombre());
        }
        producto.setCantidad(producto.getCantidad() - cantidad);
        productoRepository.save(producto);
    }

    public void aumentarStock(Long productoId, int cantidad){
        Producto producto = obtenerProductoPorId(productoId);
        producto.setCantidad(producto.getCantidad() + cantidad);
        productoRepository.save(producto);
    }

    public Boolean hayStockDisponible(Long productoId, int cantidad){
        Producto producto = obtenerProductoPorId(productoId);
        return producto.getCantidad() >= cantidad;
    }
}
