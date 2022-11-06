package ues.edu.sv.packages_tracking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.entities.Rol;
import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.service.AgenciaService;
import ues.edu.sv.packages_tracking.service.DepartmentService;
import ues.edu.sv.packages_tracking.service.RolService;
import ues.edu.sv.packages_tracking.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value = "/users")
public class UsuarioController {
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

    @GetMapping(value="")
    public String allUsersView(Model model) {
        List<Users> users = uService.findAll();
        model.addAttribute("list", users);
        return "admin/users/listado_usuarios";
    }

    /**
     * Create users
     */

    @GetMapping(value="/create")
    public String getCreateUserView(Model model, Users user) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user", user);
        map.put("agencias", agenciaService.findAll());
        map.put("departamentos", departmentService.findAll());
        map.put("roles", rolService.getAllRoles());
        model.addAllAttributes(map);
        return "admin/users/create_user";
    }

    @PostMapping(value="/register")
    public String createUser(@ModelAttribute("user") Users user) {
        
        List<Rol> roles = user.getRoles().stream().collect(Collectors.toList()); 
        System.out.println(roles.get(0).getName());
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        uService.guardarUsuario(user);

        return "redirect:/users";
    }

    /**
     * Update user methods
     */
    @GetMapping(value="/update/{id}")
    public String getUpdateUserView(Model model, Users user, @PathVariable("id")Integer id) {
        Map<String,Object> map = new HashMap<String,Object>();
        user=uService.obtenerPorId(id);
        map.put("uUser", user);
        map.put("agencias", agenciaService.findAll());
        map.put("departamentos", departmentService.findAll());
        map.put("roles", rolService.getAllRoles());
        model.addAllAttributes(map);
        System.out.println("SALIENDO DEL GET");
        return "admin/users/update_user";
    }


    @PostMapping(value="/update/{id}")
        public String updateUser(@ModelAttribute("uUser") Users user, @PathVariable("id")Integer id) {
            System.out.println("ENTRANDO AL POST");
            //Users update = uService.obtenerPorId(id);
            List<Rol> roles = user.getRoles().stream().collect(Collectors.toList()); 
            for (Rol rol : roles) {
                System.out.println(rol.getName());
            }
            System.out.println(user.getPassword());
            user.setUserId(id);
            

            return "redirect:/users";
        }
     
}
