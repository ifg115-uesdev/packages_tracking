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
import org.springframework.web.bind.annotation.PathVariable;

import ues.edu.sv.packages_tracking.entities.Rol;
import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.security.dto.Login;
import ues.edu.sv.packages_tracking.service.AgenciaService;
import ues.edu.sv.packages_tracking.service.DepartmentService;
import ues.edu.sv.packages_tracking.service.RolService;
import ues.edu.sv.packages_tracking.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value="/")
public class AuthController {
    @Autowired
    AgenciaService agenciaService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    RolService rolService;

    @Autowired
    UsuarioService uService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value="auth/login")
    public String getLoginView(Model model, Login login) {
        //model.addAttribute("login", login);
        return "login";
    }

    @GetMapping(value="")
    public String getIndex() {
        return "index";
    }
    

    @GetMapping(value="users/create")
    public String getCreateUserView(Model model, Users user) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user", user);
        map.put("agencias", agenciaService.findAll());
        map.put("departamentos", departmentService.findAll());
        map.put("roles", rolService.getAllRoles());
        model.addAllAttributes(map);
        return "admin/users/create_user";
    }

    @GetMapping(value="home")
    public String getHomeView(Model model) {
        return "home";
    }

    @PostMapping(value="users/register")
    public String createUser(@ModelAttribute("user") Users user) {
        
        List<Rol> roles = user.getRoles().stream().collect(Collectors.toList()); 
        System.out.println(roles.get(0).getName());
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        uService.guardarUsuario(user);

        return "redirect:/home";
    }

    @GetMapping(value="users/update/{id}")
    public String getUpdateUserView(Model model, Users user) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uUser", user);
        map.put("agencias", agenciaService.findAll());
        map.put("departamentos", departmentService.findAll());
        map.put("roles", rolService.getAllRoles());
        model.addAllAttributes(map);
        return "users/create_user";
    }


    @PostMapping(value="users/update/{id}")
        public String updateUser(@ModelAttribute("uUser") Users user, @PathVariable("id")Integer id) {
            
            List<Rol> roles = user.getRoles().stream().collect(Collectors.toList()); 
            System.out.println(roles.get(0).getName());
            user.setUserId(id);
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            uService.guardarUsuario(user);

            return "redirect:/home";
        }
    
    
    

}
