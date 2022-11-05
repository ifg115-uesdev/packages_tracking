package ues.edu.sv.packages_tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.entities.Transportation;
import ues.edu.sv.packages_tracking.service.TransportationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value="/transportation")
public class TransportationController {
    
    @Autowired
    TransportationService service;

    @GetMapping(value="/all")
    public String transportationList(Model model) {
        List<Transportation> list = service.getAllTransportation();
        model.addAttribute("list", list);
        return "admin/transportation/listado_transportes";
    }

    @GetMapping(value="/register")
    public String createTransportationView(Model model) {
        Transportation transportation = new Transportation();
        model.addAttribute("transportation", transportation);
        return "admin/transportation/crear_transporte";
    }

    @GetMapping(value="/update/{id}")
    public String updateTransportationView(@PathVariable("id")Integer id,Model model) {
        Transportation transportation = service.getOneById(id).get();
        model.addAttribute("uTransportation", transportation);
        return "admin/transportation/editar_transporte";
    }

    @PostMapping(value="/create")
    public String createTransportation(@ModelAttribute("transportation") Transportation transportation) {
        if (transportation!=null) {
            System.out.println(transportation.getName());
            System.out.println(transportation.isState());
            service.saveTransportation(transportation);
            return "redirect:/admin/transportation/all";
        }
        return "redirect:/admin/transportation/register";
    }
    
    @PostMapping(value="/update/{id}")
    public String updateTransportation(@PathVariable("id")Integer id, @ModelAttribute("uTransportation")Transportation transportation) {
        if (transportation!=null) {
            transportation.setTransportationId(id);
            service.saveTransportation(transportation);
            return "redirect:/admin/transportation/all";
        }
        return "redirect:/admin/transportation/update/"+id;
    }
    
}
