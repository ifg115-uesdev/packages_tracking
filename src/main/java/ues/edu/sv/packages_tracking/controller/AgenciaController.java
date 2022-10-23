package ues.edu.sv.packages_tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.service.AgenciaService;

@Controller
@RequestMapping(value = "/agencia")
public class AgenciaController {
    @Autowired
    private AgenciaService agenciaService;

    @GetMapping(value="edit_agencia")
    public String editar(){
        return"crear_editar_agencias.html";

    }


}
