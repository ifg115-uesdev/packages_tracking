package ues.edu.sv.packages_tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.entities.Agency;
import ues.edu.sv.packages_tracking.service.AgenciaService;

@Controller
public class AgenciaController {
    @GetMapping("/guardar")
    private String Save(Model model ){
        model.addAttribute("agence", new Agency());
        return "crear_editar_agencias.html";
    }

    @Autowired
    private AgenciaService agenciaService;



    @PostMapping("/guardar")
    public String saveAgency (@ModelAttribute Agency agence, Model model){
        agenciaService.guardarAgencia(agence);
        System.out.println("Guardado");
        return "listado_agencias";
    }
}
