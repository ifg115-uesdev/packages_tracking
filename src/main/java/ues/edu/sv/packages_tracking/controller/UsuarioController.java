package ues.edu.sv.packages_tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/users")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @GetMapping(value="")
    public String allUsersView(Model model) {
        List<Users> users = service.findAll();
        model.addAttribute("list", users);
        return "admin/users/listado_usuarios";
    }
    
}
