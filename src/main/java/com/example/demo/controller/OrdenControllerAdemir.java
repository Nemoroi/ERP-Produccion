package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	

}
