package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
//import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioID")
    private Integer usuarioID;

    @Column(name = "Nombre_Completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "Usuario", nullable = false, unique = true)
    private String user;

    @Column(name = "Clave", nullable = false)
    private String clave;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Telefono")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoUsuario estado = EstadoUsuario.Activo;

    @Column(name = "Fecha_Registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
  
    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;
/*    
    @OneToMany(mappedBy = "jefe")
    private List<Area> areasJefe;

    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes;

    @OneToMany(mappedBy = "manager")
    private List<Reporte> reportesManager;

*/
    public enum EstadoUsuario {
        Activo, Inactivo
    }
}