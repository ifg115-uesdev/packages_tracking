package ues.edu.sv.packages_tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ues.edu.sv.packages_tracking.entities.StatePackage;

@Repository
public interface StatePackageRepository extends JpaRepository<StatePackage,Integer>{
    
}
