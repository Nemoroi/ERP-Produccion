package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Rol")
@Getter @Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RolID")
    private Integer rolID;

    @Column(name = "Nombre_Rol", nullable = false, unique = true)
    private String nombreRol;

    @Column(name = "Descripcion", columnDefinition = "TEXT")
    private String descripcion;


    
    // Relaciones
    @OneToMany(mappedBy = "rol")	
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "rol")
    private List<RolVistas> rolVistas;

    
}