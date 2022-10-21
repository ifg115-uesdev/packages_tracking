package ues.edu.sv.packages_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Grupo10Controller {
    @RequestMapping(value = "/login")
    public String loginView() {
        return "login";
    }

    @RequestMapping(value = "/consultarTransporte")
    public String consultTransportationView() {
        return "ConsultarTransporte";
    }
}