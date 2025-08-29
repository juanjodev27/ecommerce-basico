package com.example.mi_ecommerze.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetalleDePedidos> detalleDePedidos = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime fechaCreacion;

    @NotNull
    @DecimalMin(value = "0.0", message = "El precio total no debe estar vacio")
    private BigDecimal precioTotal;

    @NotBlank(message = "La direccion es obligatorio")
    @Size(max = 500, message = "La direccion debe tener hasta 500 caracteres")
    private String direccionEnvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    public enum EstadoPedido{
        PENDIENTE("Pendiente"),
        CONFIRMADO("Confirmado"),
        PROCESANDO("Procesando"),
        ENVIADO("Enviado"),
        ENTREGADO("Entregado"),
        CANCELADO("Cancelado"),
        DEVUELTO("Devuelto");

        private final String descripcion;

        EstadoPedido(String descripcion){
            this.descripcion = descripcion;
        }

        private String getDescripcion(){
            return descripcion;
        }
    }
}
