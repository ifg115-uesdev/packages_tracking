package ues.edu.sv.packages_tracking.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ues.edu.sv.packages_tracking.entities.Rol;
import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.repository.AgenciaRepository;
import ues.edu.sv.packages_tracking.repository.DepartamentoRepository;
import ues.edu.sv.packages_tracking.repository.RolRepository;
import ues.edu.sv.packages_tracking.security.dto.Login;
import ues.edu.sv.packages_tracking.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping(value="/")
public class AuthController {
    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UsuarioService uService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value="auth/login")
    public String getLoginView(Model model, Login login) {
        //model.addAttribute("login", login);
        return "login";
    }

    @PostMapping(value="auth/login")
    public String getIndex() {
        return "redirect:/";
    }
    

    @GetMapping(value="auth/create")
    public String getCreateUserView(Model model, Users user) {
        Map<String,Object> objetos = new HashMap<String,Object>();
        objetos.put("user", user);
        objetos.put("agencias", agenciaRepository.findAll());
        objetos.put("departamentos", departamentoRepository.findAll());
        objetos.put("roles", rolRepository.findAll());
        model.addAllAttributes(objetos);
        return "users/create_user";
    }

    @GetMapping(value="home")
    public String getHomeView(Model model) {
        return "home";
    }

    @PostMapping(value="auth/register")
    public String createUser(@ModelAttribute("user") Users user) {
        System.out.println(user.getName());
        System.out.println(user.getLastname());
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getGender());
        System.out.println(user.getDepartmentId().getName());
        System.out.println(user.getAgencyId().getName());
        System.out.println(user.getDateBirth());
        
        List<Rol> roles = user.getRoles().stream().collect(Collectors.toList()); 
        System.out.println(roles.get(0).getName());
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        uService.guardarUsuario(user);

        return "redirect:/home";
    }
    
    
    

}
