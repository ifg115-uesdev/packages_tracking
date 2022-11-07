package ues.edu.sv.packages_tracking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ues.edu.sv.packages_tracking.entities.StatePackage;
import ues.edu.sv.packages_tracking.entities.TrackingHist;
import ues.edu.sv.packages_tracking.entities.Package;
import ues.edu.sv.packages_tracking.entities.Transportation;
import ues.edu.sv.packages_tracking.service.PackageService;
import ues.edu.sv.packages_tracking.service.StatePackageService;
import ues.edu.sv.packages_tracking.service.TrackingHistService;
import ues.edu.sv.packages_tracking.service.TransportationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value="/transportation")
public class TransportationController {

    private List<Package> packages = new ArrayList<>();
    private Integer transportationId = 0;
    
    @Autowired
    TransportationService service;

    @Autowired
    PackageService packageService;

    @Autowired
    StatePackageService statePackageService;

    @Autowired
    TrackingHistService trackingHistService;

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
            return "redirect:/transportation/all";
        }
        return "redirect:/transportation/all";
    }
    
    @PostMapping(value="/update/{id}")
    public String updateTransportation(@PathVariable("id")Integer id, @ModelAttribute("uTransportation")Transportation transportation) {
        if (transportation!=null) {
            transportation.setTransportationId(id);
            service.saveTransportation(transportation);
            return "redirect:/transportation/all";
        }
        return "redirect:/transportation/update/"+id;
    }
    
    @GetMapping(value="/packages")
    public String getViewTransportationPackage() {
        return "consultar_transporte";
    }

    @GetMapping(value="/packages-transportation")
    public String getPackagesByTransportationId(@RequestParam("transportationId") Integer id ,Model model) {
        List<Package> list = packageService.findByTransportationId(id);
        List<StatePackage> stateList = statePackageService.getAllStates().stream().filter(s -> s.getStatePackageId() > 5).collect(Collectors.toList());

        Map<String,Object> map= new HashMap<>();
        map.put("paquetes", list);
        map.put("estados", stateList);
        model.addAllAttributes(map);
        this.setPackages(list);
        this.setTransportationId(id);
        return "consultar_transporte";
    }

    @PostMapping(value="/update-state-package")
    public String updateTransportation(@RequestParam("stateP") Integer stateP) {
        
        this.getPackages().stream().forEach(p -> {
            TrackingHist tra = new TrackingHist();

            tra.setPackageId(p);
            tra.setModifyDate(new Date());
            tra.setAgencyId(p.getShippingAgencyId());
            tra.setStatePackageId(new StatePackage(stateP));
            tra.setTransportationId(new Transportation(getTransportationId()));
            tra.setObservation("Inconvenientes con el transporte en el cual se encontraba el paquete");
            this.trackingHistService.save(tra);
        });
        return "redirect:/transportation/packages-transportation?transportationId=1";
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public Integer getTransportationId() {
        return transportationId;
    }

    public void setTransportationId(Integer transportationId) {
        this.transportationId = transportationId;
    }

    
}
