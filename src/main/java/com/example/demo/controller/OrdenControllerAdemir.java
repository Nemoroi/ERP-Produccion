package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.OrdenAdemir;
import com.example.demo.service.OrdenServiceAdemir;

@Controller
@RequestMapping("/ordenes")

public class OrdenControllerAdemir {
	@Autowired
	private OrdenServiceAdemir ordenService;
	
	@GetMapping("/listar")
    public String listar(Model model) {
        List<OrdenAdemir> lstOrden = ordenService.listarOrdenList();
        model.addAttribute("ordenes", lstOrden);
        return "ordenes/listar";
    }
	
	@GetMapping("/cerrar")
	public String cerrar(Model model) {
	    List<OrdenAdemir> lstCerrarOrden = ordenService.listarOrdenList().stream().filter(o -> !"Cerrada".equalsIgnoreCase(o.getEstado())).toList();

	    model.addAttribute("ordenes", lstCerrarOrden);
	    return "ordenes/cerrar";
	}
	
	@PostMapping("/cerrar/{id}")
	public String cerrarOrden(@PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
	    ordenService.cerrarOrden(id);
	    redirectAttrs.addFlashAttribute("msg", "Orden cerrada correctamente");
	    return "redirect:/ordenes/cerrar";
	}


}
