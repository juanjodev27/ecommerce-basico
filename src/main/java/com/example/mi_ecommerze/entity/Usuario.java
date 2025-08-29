package com.example.mi_ecommerze.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El formato del email no es valido")
    private String email;

    @NotBlank(message = "La direccion no puede estar vacia")
    @Size(max = 500, message = "La direccion debe tener hasta 500 caracteres")
    private String direccion;

    @Min(value = 18, message = "La edad debe de ser mayor a 18")
    private int edad;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime ultimaModificacion;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Carrito carrito;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    enum EstadoUsuario{
        ACTIVO,INACTIVO,SUSPENDIDO
    }
}
