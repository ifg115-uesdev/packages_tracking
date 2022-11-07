package ues.edu.sv.packages_tracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ues.edu.sv.packages_tracking.entities.Agency;

@Repository
public interface AgenciaRepository extends JpaRepository<Agency, Integer>{
    
    @Query("SELECT a FROM Agency a WHERE a.name = :name")
    Optional<Agency> findByAgencyName(@Param("name") String name );
}
