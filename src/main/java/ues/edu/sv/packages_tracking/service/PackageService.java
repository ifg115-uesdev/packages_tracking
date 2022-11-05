package ues.edu.sv.packages_tracking.service;

import ues.edu.sv.packages_tracking.entities.Package;

public interface PackageService {
    
    public Package findById(String id);

    public void savePackage(Package paquete);
}
