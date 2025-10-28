package com.example.demo.repository;

import com.example.demo.model.Vistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VistaRepository extends JpaRepository<Vistas, Integer> {

    @Query("""
           SELECT v 
           FROM Vistas v 
           JOIN v.rolVistas rv 
           WHERE rv.rol.rolID = :rolID
           """)
    List<Vistas> obtenerVistasPorRol(@Param("rolID") Integer rolID);
}
