package com.example.demo.service.impl;

import com.example.demo.model.RolVistas;
import com.example.demo.model.Vistas;
import com.example.demo.repository.RolVistaRepository;
import com.example.demo.repository.VistaRepository;
import com.example.demo.service.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SesionServiceImpl implements SesionService {

    private final RolVistaRepository rolVistasRepository;
    private final VistaRepository vistasRepository;

    @Override
    public List<Vistas> obtenerVistasPorRol(Integer rolID) {
        return vistasRepository.obtenerVistasPorRol(rolID);

    }

    @Override
    public List<RolVistas> obtenerRolVistasPorRol(Integer rolID) {
        return rolVistasRepository.obtenerRolVistasPorRol(rolID);
    }
}