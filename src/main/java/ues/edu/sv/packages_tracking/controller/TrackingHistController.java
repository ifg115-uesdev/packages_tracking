package ues.edu.sv.packages_tracking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ues.edu.sv.packages_tracking.service.PackageService;
import ues.edu.sv.packages_tracking.service.TrackingHistService;

@Controller
@RequestMapping("/tracking")
public class TrackingHistController {
    @Autowired
    TrackingHistService histService;

    @Autowired
    PackageService packageService;


    @GetMapping(value="/home")
    public String getHomeClientView() {
        return "cliente/home_cliente";
    }

    @GetMapping("/detail")
    public String detailHistoryPackageView(@RequestParam("packageId") String packageId,Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("paquete", packageService.findById(packageId));
        map.put("history",histService.findAllByPackaeId(packageId));
        model.addAllAttributes(map);
        System.out.println(packageId);
        return "cliente/historial_envio";
    }
}
