package ues.edu.sv.packages_tracking.service;

import java.util.List;

import ues.edu.sv.packages_tracking.entities.Package;

public interface PackageService {
    
    public Package findById(String id);

    public List<Package> findByTransportationId(Integer transportationId);

    public void savePackage(Package paquete);

    public boolean existe(String packageId);
}
