package ues.edu.sv.packages_tracking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ues.edu.sv.packages_tracking.entities.Agency;
import ues.edu.sv.packages_tracking.entities.AgencyType;
import ues.edu.sv.packages_tracking.entities.Department;
import ues.edu.sv.packages_tracking.service.AgenciaService;
import ues.edu.sv.packages_tracking.service.AgencyTypeService;
import ues.edu.sv.packages_tracking.service.DepartmentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * Clase controladora para las agencias
 */
@Controller
@RequestMapping(value = "/agencia")
public class AgenciaController {

    @Autowired
    AgenciaService agenciaService;
    @Autowired
    AgencyTypeService agencyTypeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value="/list")
    public String allAgenciesView(Model model) {
        List<Agency> agencias = agenciaService.findAll();
        model.addAttribute("agencias", agencias);
        return "agency/listado_agencias";
    }
    

    @GetMapping(value="/create")
    public String createNewAgencyView(Model model, Agency agencia) {
        List<AgencyType> types = agencyTypeService.findAll();
        List<Department> departments= departmentService.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("agency", agencia);
        map.put("types", types);
        map.put("departments",departments);
        model.addAllAttributes(map);
        return "agency/crear_agencia";
    }

    @GetMapping(value="/update/{id}")
    public String updateAgencyView(@PathVariable("id")Integer id,Model model) {
        Agency agency = agenciaService.obtenerPorId(id);
        List<AgencyType> types = agencyTypeService.findAll();
        List<Department> departments= departmentService.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("uAgency", agency);
        map.put("types", types);
        map.put("departments",departments);
        model.addAllAttributes(map);
        return "agency/editar_agencia";
    }

    @PostMapping(value="/create")
    public String createNewAgency(@ModelAttribute("agency") Agency agencia) {
        if (agencia !=null) {
            System.out.println(agencia.toString());
            System.out.println(agencia.getAgencyTypeId().getType()+" "+agencia.getDepartmentId().getName());
            agenciaService.guardarAgencia(agencia);
            return "redirect:/agencia/list";
        }
        return "redirect:/agencia/create";
    }
    
    @PostMapping(value="/update/{id}")
    public String updateTransportation(@PathVariable("id")Integer id, @ModelAttribute("uTransportation")Agency agency) {
        if (agency !=null) {
            System.out.println(agency.toString());
            System.out.println(agency.getAgencyTypeId().getType()+" "+agency.getDepartmentId().getName());
            agency.setAgencyId(id);
            agenciaService.guardarAgencia(agency);
            return "redirect:/agencia/list";
        }
        return "redirect:/agencia/update/"+id;
    }
    
}
