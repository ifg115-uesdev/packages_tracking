package ues.edu.sv.packages_tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ues.edu.sv.packages_tracking.entities.User;
import ues.edu.sv.packages_tracking.service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //devuelve la vista crear y editar usuarios (esto es responsabilidad de Karla Stefany Menéndez Román mr17054)
    //lo hice aqui para ver esta vista y hacer mis pruebas
    @GetMapping("/editar")
    public String openView(Model model){
        model.addAttribute("user", new User());
        System.out.println("abriendo vista...");
        return "crear_editar_usuarios";
    }
    
    //no me daje guardar el usuario en la base de datos porque me pide agencyId y falta esa opcion
    //en la vista, por eso el metodo para guardar esta comentado
    @PostMapping("/editar")
    public String saveUser(@ModelAttribute User user, Model model) {
      //usuarioService.guardarUsuario(user);
      System.out.println("usuario guardado");
      return "listado_usuarios";
    } 
    

}