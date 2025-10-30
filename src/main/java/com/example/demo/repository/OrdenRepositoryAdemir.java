package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.OrdenAdemir;

import jakarta.transaction.Transactional;

public interface OrdenRepositoryAdemir extends JpaRepository<OrdenAdemir, Integer>{

	@Modifying
    @Transactional
    @Query("UPDATE OrdenAdemir o SET o.estado = 'Cerrada' WHERE o.idOrden = :id")
    void cerrarOrden(@Param("id") Integer id);
}
