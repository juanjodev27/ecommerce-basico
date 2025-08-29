package com.example.mi_ecommerze.repository;

import com.example.mi_ecommerze.entity.Carrito;
import com.example.mi_ecommerze.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByUsuario(Usuario usuario);
}
