package ues.edu.sv.packages_tracking.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ues.edu.sv.packages_tracking.entities.Package;
import ues.edu.sv.packages_tracking.entities.TrackingHist;
import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.service.AgenciaService;
import ues.edu.sv.packages_tracking.service.PackageService;
import ues.edu.sv.packages_tracking.service.StatePackageService;
import ues.edu.sv.packages_tracking.service.TrackingHistService;
import ues.edu.sv.packages_tracking.service.TransportationService;
import ues.edu.sv.packages_tracking.service.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/package")
public class PackageController {
    
    @Autowired
    PackageService packageService;

    @Autowired
    AgenciaService agenciaService;

    @Autowired
    UsuarioService uService;

    @Autowired
    TrackingHistService hService;

    @Autowired
    StatePackageService stateService;

    @Autowired
    TransportationService tService;

    @GetMapping(value="/create")
    public String createPackageView(Model model) {
        Map<String,Object> map = new HashMap<>();
        Package paquete = new Package();
        map.put("agencias", agenciaService.findAll());
        map.put("paquete", paquete);
        model.addAllAttributes(map);

        return "atencion_cliente/registro_paquete";
    }

    @PostMapping(value="/create")
    public String createPackage(@ModelAttribute("paquete")Package paquete,@RequestParam("username") String username) {
        if (paquete!=null && (username!="" && username!=null )) {
            Users userId = uService.getByUsername(username);
            TrackingHist history = new TrackingHist();

            /**Generando id para el paquete y guardando los datos del paquete */
            String idPackage = RandomStringUtils.randomAlphanumeric(25);
            while (packageService.existe(idPackage)) {
                idPackage = RandomStringUtils.randomAlphanumeric(25);
            }
            paquete.setPackageId(idPackage);
            paquete.setUserId(userId);
            double precio = paquete.getDestinationAgencyId().getDepartmentId().getRate()+paquete.getShippingAgencyId().getDepartmentId().getRate();
            paquete.setPrice((float)precio);
            paquete.setStartDate(new Date());
            packageService.savePackage(paquete);
            /**Generando el historial del envio del paquete */
            history.setPackageId(paquete);
            history.setModifyDate(new Date());
            history.setAgencyId(userId.getAgencyId());
            history.setTransportationId(tService.getOneById(1).get());
            history.setStatePackageId(stateService.getOneById(1).get());
            hService.save(history);

            return "redirect:/package/info/"+idPackage;
        }
        return "redirect:/package/create?error";
    }

    @GetMapping("/info/{packageId}")
    public String infoPackage(@PathVariable("packageId")String packageId,Model model) {
        
        model.addAttribute("paquete", packageService.findById(packageId));  
        return "cliente/info_envio";
    }
    
    
}
