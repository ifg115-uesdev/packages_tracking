package ues.edu.sv.packages_tracking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ues.edu.sv.packages_tracking.entities.AgencyType;

@Repository
public interface TipoAgenciaRepository extends CrudRepository<AgencyType, Integer>{
    
}
