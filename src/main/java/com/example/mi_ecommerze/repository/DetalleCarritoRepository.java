package com.example.mi_ecommerze.repository;

import com.example.mi_ecommerze.entity.Carrito;
import com.example.mi_ecommerze.entity.DetalleCarrito;
import com.example.mi_ecommerze.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {


    Optional<DetalleCarrito> findByCarritoAndProducto(Carrito carrito, Producto producto);
}
