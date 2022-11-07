package ues.edu.sv.packages_tracking.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ues.edu.sv.packages_tracking.entities.Package;
import ues.edu.sv.packages_tracking.entities.StatePackage;
import ues.edu.sv.packages_tracking.entities.TrackingHist;
import ues.edu.sv.packages_tracking.entities.Transportation;
import ues.edu.sv.packages_tracking.service.AgenciaService;
import ues.edu.sv.packages_tracking.service.PackageService;
import ues.edu.sv.packages_tracking.service.StatePackageService;
import ues.edu.sv.packages_tracking.service.TrackingHistService;
import ues.edu.sv.packages_tracking.service.TransportationService;

@Controller
@RequestMapping("/tracking")
public class TrackingHistController {
    @Autowired
    TrackingHistService histService;

    @Autowired
    PackageService packageService;

    @Autowired
    StatePackageService stateService;

    @Autowired
    AgenciaService aService;

    @Autowired
    TransportationService tService;


    @GetMapping(value="/home")
    public String getHomeClientView() {
        return "cliente/home_cliente";
    }

    @GetMapping("/detail")
    public String detailHistoryPackageView(@RequestParam("packageId") String packageId,Model model) {
        Map<String, Object> map = new HashMap<>();
        Package paquete = packageService.findById(packageId);
        List<TrackingHist> history = histService.findAllByPackaeId(packageId).stream().sorted(Comparator.comparingInt(TrackingHist::getHistId).reversed()).collect(Collectors.toList());
        paquete.setTrackingHistList(history);
        map.put("paquete", paquete);
        map.put("history",history);
        model.addAllAttributes(map);
        System.out.println(packageId);
        return "cliente/historial_envio";
    }

    @GetMapping("change-state")
    public String ChangeStatePackageView(Model model, Package paquete) {
        model.addAttribute("paquete",paquete);
        return "tecnico_bodega/cambio_estado";
    }

    @GetMapping("changestate")
    public String getChangeStatePackageView(Model model, @RequestParam("packageId")String packageId,Package paquete) {
        paquete = packageService.findById(packageId);
        List<TrackingHist> history = paquete.getTrackingHistList().stream().sorted(Comparator.comparingInt(TrackingHist::getHistId).reversed()).collect(Collectors.toList());
        paquete.setTrackingHistList(history);
        model.addAttribute("package",paquete);
        return "tecnico_bodega/procesar_cambio";
    }

    @PostMapping("/changestate")
    public String changeStatePackage(@RequestParam("idPackage")String packageId) {
        Package paquete = packageService.findById(packageId);
        TrackingHist newHistory= new TrackingHist();
        TrackingHist trackingHist=null;
        List<TrackingHist> history = histService.findAllByPackaeId(packageId).stream().sorted(Comparator.comparingInt(TrackingHist::getHistId).reversed()).collect(Collectors.toList());
        StatePackage estado =null;
        trackingHist=history.get(0);
            switch (trackingHist.getStatePackageId().getStatePackageId()) {
                case 1:
                estado = stateService.getOneById(2).get();
                newHistory.setPackageId(paquete);
                newHistory.setAgencyId(aService.obtenerPorNombre("Agencia San Salvador"));
                newHistory.setModifyDate(new Date());
                newHistory.setStatePackageId(estado);
                newHistory.setTransportationId(trackingHist.getTransportationId());
                histService.save(newHistory);
                    break;
                case 2:
                    estado = stateService.getOneById(3).get();
                    newHistory.setPackageId(paquete);
                    newHistory.setAgencyId(aService.obtenerPorNombre("Agencia San Salvador"));
                    newHistory.setModifyDate(new Date());
                    newHistory.setStatePackageId(estado);
                    newHistory.setTransportationId(trackingHist.getTransportationId());
                    histService.save(newHistory);
                    break;
                case 3:
                    estado = stateService.getOneById(4).get();
                    newHistory.setPackageId(paquete);
                    newHistory.setAgencyId(paquete.getDestinationAgencyId());
                    newHistory.setModifyDate(new Date());
                    newHistory.setStatePackageId(estado);
                    newHistory.setTransportationId(tService.getOneById(2).get());
                    histService.save(newHistory);
                    break;
                case 4:
                    estado = stateService.getOneById(5).get();
                    newHistory.setPackageId(paquete);
                    newHistory.setAgencyId(paquete.getDestinationAgencyId());
                    newHistory.setModifyDate(new Date());
                    newHistory.setStatePackageId(estado);
                    newHistory.setTransportationId(tService.getOneById(2).get());
                    histService.save(newHistory);
                    break;
                
                // default:
                //     int estadoAnterior =  history.get(1).getStatePackageId().getStatePackageId();
                //     break;
        }
        
        return "redirect:/tracking/home";
    }
}
