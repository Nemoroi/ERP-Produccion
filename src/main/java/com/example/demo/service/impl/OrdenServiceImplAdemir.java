package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.OrdenAdemir;
import com.example.demo.repository.OrdenRepositoryAdemir;
import com.example.demo.service.OrdenServiceAdemir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdenServiceImplAdemir implements OrdenServiceAdemir {

	private final OrdenRepositoryAdemir ordenRepositoryAdemir;

	@Override
	public List<OrdenAdemir> listarOrdenList() {
		return ordenRepositoryAdemir.findAll();
	}

	@Transactional
	@Override
	public void cerrarOrden(Integer id) {
	    ordenRepositoryAdemir.cerrarOrden(id);
	}

}
