package com.example.demo.repository;

import com.example.demo.model.RolVistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolVistaRepository extends JpaRepository<RolVistas, Integer> {

    @Query("SELECT rv FROM RolVistas rv WHERE rv.rol.rolID = :rolID")
    List<RolVistas> obtenerRolVistasPorRol(@Param("rolID") Integer rolID);
}
