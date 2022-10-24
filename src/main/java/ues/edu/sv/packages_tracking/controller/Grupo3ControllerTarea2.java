package ues.edu.sv.packages_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Grupo3ControllerTarea2 {
     @GetMapping("/Grupo3T2")
    public String inicio(Model model){
        return "Grupo3T2";
    }
}
