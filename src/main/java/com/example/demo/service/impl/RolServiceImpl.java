package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Rol;
import com.example.demo.repository.RolRepository;

import lombok.RequiredArgsConstructor;
import com.example.demo.service.RolService;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

	private final RolRepository rolRepository;
	

	public List<Rol> listaRoles(){
		return rolRepository.findAll();
	}
}
	