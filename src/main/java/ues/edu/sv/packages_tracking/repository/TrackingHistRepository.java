package ues.edu.sv.packages_tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ues.edu.sv.packages_tracking.entities.TrackingHist;

@Repository
public interface TrackingHistRepository extends JpaRepository<TrackingHist,Integer>{
    
    @Query("SELECT p FROM TrackingHist p WHERE p.packageId.packageId = :packageId")
    List<TrackingHist> findByPackageId(@Param("packageId")String packageId);
}
