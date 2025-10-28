package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.RolVistas;
import com.example.demo.repository.RolVistaRepository;
import com.example.demo.service.RolVistaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolVistaServiceImpl implements RolVistaService{

	 private final RolVistaRepository rolVistaRepository;
	 
	 public List<RolVistas> listaRolVistaList(){
		 return rolVistaRepository.findAll();
	 }
}
