package com.example.demo.service;

import com.example.demo.model.Vistas;
import com.example.demo.model.RolVistas;
import java.util.List;

public interface SesionService {
    List<Vistas> obtenerVistasPorRol(Integer rolID);

    List<RolVistas> obtenerRolVistasPorRol(Integer rolID);
}