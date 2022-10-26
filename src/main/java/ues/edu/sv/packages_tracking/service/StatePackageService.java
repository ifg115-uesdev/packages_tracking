package ues.edu.sv.packages_tracking.service;

import java.util.List;
import java.util.Optional;

import ues.edu.sv.packages_tracking.entities.StatePackage;

public interface StatePackageService {
    public List<StatePackage> getAllStates();

    public Optional<StatePackage> getOneById(Integer id);
}
