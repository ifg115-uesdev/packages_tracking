package ues.edu.sv.packages_tracking.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ues.edu.sv.packages_tracking.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    //@Qualifier("usuarioservice")
    private UsuarioService usuarioservice;
    @GetMapping(value = "/all-usuario")
    
    public ModelAndView listAllUsuarios(){

        ModelAndView mav = new ModelAndView("listado_usuario.html");
        mav.addObject("usuarios", usuarioservice.findAll());
        return mav;
    }

   /* @GetMapping(value="/all-usuario")
    public String getAllUsuarioView(Model modelo){
        modelo.addAttribute("usuarios", servicio.findAll());
        return "listado_usuarios.html";
    }*/
}
