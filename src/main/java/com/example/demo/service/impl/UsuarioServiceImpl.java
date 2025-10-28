package com.example.demo.service.impl;

import com.example.demo.dto.ResultadoResponse;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findByUsuario(String usuario) {
        return usuarioRepository.findByUser(usuario);
    }


    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuariosList() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }


	@Override
	public ResultadoResponse create(Usuario usuario) {
		try {
			Usuario usuarioRegistrado = usuarioRepository.save(usuario);
			
			String mensaje = String.format("Usuario registrado con Id %s", usuarioRegistrado.getUsuarioID());
			return new ResultadoResponse(true, mensaje);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en UsuarioService: "+ e.getMessage());
		}
	}


	@Override
	public ResultadoResponse update(Usuario usuario) {
		try {
			Usuario usuarioRegistrado = usuarioRepository.save(usuario);
			
			String mensaje = String.format("Usuario actualizado con Id %s", usuarioRegistrado.getUsuarioID());
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en UsuarioService: "+ e.getMessage());
		}
		
	}


	@Override
	public void eliminarUsuario(Integer id) {
		usuarioRepository.deleteById(id);
		
	}

    }
