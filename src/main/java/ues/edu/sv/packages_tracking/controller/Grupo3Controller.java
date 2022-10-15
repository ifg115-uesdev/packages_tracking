package ues.edu.sv.packages_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Grupo3Controller {
     @GetMapping("/")
    public String inicio(Model model){
        return "Vista3";
    }
}
