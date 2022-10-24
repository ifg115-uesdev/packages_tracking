package ues.edu.sv.packages_tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.service.AgenciaService;

@Controller
public class AgenciaController {
    @RequestMapping(value = "/Guardar")
    private String Save(){
        return "listado_agencias.html";
    }

    @Autowired
    private AgenciaService agenciaService;
}
