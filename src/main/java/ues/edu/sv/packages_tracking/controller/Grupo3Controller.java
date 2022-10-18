package ues.edu.sv.packages_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/create-user")
public class Grupo3Controller {
    @GetMapping("/")
    public String inicio(Model model) {
        return "example";
    }
}
