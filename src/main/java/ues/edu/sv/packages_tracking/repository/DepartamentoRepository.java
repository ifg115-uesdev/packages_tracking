package ues.edu.sv.packages_tracking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ues.edu.sv.packages_tracking.entities.Department;

@Repository
public interface DepartamentoRepository extends CrudRepository<Department, Integer>{
    
}
