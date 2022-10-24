package ues.edu.sv.packages_tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.service.AgenciaService;

@Controller
@RequestMapping(value = "/agencia")
public class AgenciaController {

    @Autowired
    private AgenciaService servicio;

    @GetMapping(value="/all-agencia")
    public String mostrarAgencias(Model modelo){
        //List<Agency> listaAgencia = servicio.findAll();
        modelo.addAttribute("agencias", servicio.findAll());       
        return "listado_agencias";
    }
}
