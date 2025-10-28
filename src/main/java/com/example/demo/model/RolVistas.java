package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rol_vista")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolVistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rol_VistaID")
    private Integer rolVistaID;

    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "VistaID", nullable = false)
    private Vistas vista;

    @Enumerated(EnumType.STRING)
    @Column(name = "Permisos", nullable = false)
    private Permiso permiso = Permiso.Lectura;

    @Column(name = "Fecha_Asignacion", updatable = false)
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    public enum Permiso {
        Lectura, Escritura, Admin
    }
}
