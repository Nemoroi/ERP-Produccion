package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Rol;
import com.example.demo.model.RolVistas;
import com.example.demo.model.Usuario;
import com.example.demo.model.Vistas;
import com.example.demo.service.RolService;
import com.example.demo.service.RolVistaService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VistaService;

@Controller 
@RequestMapping("/manejo_vistas")
public class ManejoVistasController {
	
	@Autowired
	private RolService rolService;
	@Autowired
	private RolVistaService rolVistaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private VistaService vistaService;
	
    @GetMapping("/listarRoles")
    public String listarRoles(Model model) {
    	List<Rol> listaRol = rolService.listaRoles();
    	model.addAttribute("roles", listaRol);
        return "manejo_vistas/listar_roles"; // templates/usuarios/listar.html
    }
    	
    @GetMapping("/listarRolVista")
    public String listarRolVista(Model model) {
    	List<RolVistas> listaRolVista = rolVistaService.listaRolVistaList();
    	model.addAttribute("rolVistas", listaRolVista);
        return "manejo_vistas/listar_rol_vistas"; // templates/usuarios/listar.html
    }
    
    @GetMapping("/listarVistas")
    public String listarVistas(Model model) {
    	List<Vistas> listaVistas = vistaService.listaVistaList();
    	model.addAttribute("vistas", listaVistas);
        return "manejo_vistas/listar_vistas"; // templates/usuarios/listar.html
    }
    

}
