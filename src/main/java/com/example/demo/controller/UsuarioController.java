package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Usuario;
import com.example.demo.service.RolService;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.Alert;

import org.springframework.ui.Model;

@Controller 
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuario> listaUsuarios = usuarioService.listarUsuariosList();
        model.addAttribute("usuarios", listaUsuarios);
        return "usuarios/listar"; // templates/usuarios/listar.html
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
    	model.addAttribute("usuarios", usuarioService.listarUsuariosList());
    	model.addAttribute("rol", rolService.listaRoles());
    	model.addAttribute("usuario", new Usuario());
    	return "usuarios/nuevo";
	}
    
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Usuario usario, Model model, RedirectAttributes flash) {
		var response = usuarioService.create(usario);
		
		if(!response.success) {
			model.addAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
			model.addAttribute("usuarios", usuarioService.listarUsuariosList());
	    	model.addAttribute("rol", rolService.listaRoles());
	    	return "usuarios/nuevo";
		}
		flash.addFlashAttribute("toast", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/usuarios/listar";
	}
    
    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable Integer id, Model model) {
    	model.addAttribute("rol", rolService.listaRoles());
    	model.addAttribute("usuario", usuarioService.buscarPorId(id));
    	return "usuarios/editar";
    }
    
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, Model model, RedirectAttributes flash) {
		
    	var response = usuarioService.update(usuario);
    	
    	if(!response.success) {
    		model.addAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
			model.addAttribute("usuarios", usuarioService.listarUsuariosList());
	    	model.addAttribute("rol", rolService.listaRoles());
	    	return "usuarios/nuevo";
    	}
    	flash.addFlashAttribute("toast", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/usuarios/listar";
		
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
    	usuarioService.eliminarUsuario(id);
    	 redirectAttrs.addFlashAttribute("mensaje", "Usuario eliminada correctamente ✅");
    	 return "redirect:/usuarios/listar";
    }
}