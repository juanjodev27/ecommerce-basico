package com.example.mi_ecommerze.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "detalle_pedido")
public class DetalleDePedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad no debe de ser nula")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    @Size(min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres.")
    private String nombreProducto;

    @NotNull(message = "El precio unitario no puede ser nulo.")
    @DecimalMin(value = "0.01", inclusive = true, message = "El precio unitario debe ser mayor que cero.")
    private BigDecimal precioUnitario;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonBackReference
    private Producto producto;
}
