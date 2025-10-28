package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Vistas;
import com.example.demo.repository.VistaRepository;
import com.example.demo.service.VistaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VistaServiceImpl implements VistaService{

	private final VistaRepository vistaRepository;
	
	public List<Vistas> listaVistaList(){
			return vistaRepository.findAll();
	}
}
