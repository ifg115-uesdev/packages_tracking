package ues.edu.sv.packages_tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController  {
    
    @Autowired
     private UsuarioService servicio;

     @GetMapping(value="/allUsers")
     public String editarUsuario() {
         return "listado_usuarios.html";
     }
     
    
}
