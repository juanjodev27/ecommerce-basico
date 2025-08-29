package com.example.mi_ecommerze.repository;

import com.example.mi_ecommerze.entity.Pedido;
import com.example.mi_ecommerze.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
}
