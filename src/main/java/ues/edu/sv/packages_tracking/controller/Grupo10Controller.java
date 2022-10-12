package ues.edu.sv.packages_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(value = "/main")
public class Grupo10Controller {
    @GetMapping(value = "/home")
    public String loginView() {
        return "login";
    }

}