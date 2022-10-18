package ues.edu.sv.packages_tracking.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class Grupo10Controller {


    @GetMapping
    public String loginView() {
        return "vista10";
    }
    
}
