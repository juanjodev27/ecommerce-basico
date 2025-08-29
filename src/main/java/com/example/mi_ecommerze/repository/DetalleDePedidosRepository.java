package com.example.mi_ecommerze.repository;

import com.example.mi_ecommerze.entity.DetalleDePedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleDePedidosRepository extends JpaRepository<DetalleDePedidos, Long> {
}
