package ues.edu.sv.packages_tracking.service;

import java.util.List;
import java.util.Optional;

import ues.edu.sv.packages_tracking.entities.Transportation;

public interface TransportationService {
    
    public List<Transportation> getAllTransportation();

    public Optional<Transportation> getOneById(Integer id);

    public void saveTransportation(Transportation transportation);

}
