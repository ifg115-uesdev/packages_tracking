package ues.edu.sv.packages_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
    
    @GetMapping(value="/listado_usuarios")
    public String crear(){
    return "crear_editar_usuarios.html";
        
    }
}
