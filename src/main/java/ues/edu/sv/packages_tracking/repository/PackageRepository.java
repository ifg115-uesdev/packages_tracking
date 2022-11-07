package ues.edu.sv.packages_tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ues.edu.sv.packages_tracking.entities.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, String> {

    @Query(value = "select p.* from package p inner join tracking_hist th on p.package_id = th.package_id where th.transportation_id = :id and th.modify_date > (select t.modify_date from tracking_hist t where t.transportation_id != :id and t.package_id = p.package_id order by t.modify_date desc limit 1)", nativeQuery = true)
    List<Package> findByTransportationId(@Param("id") Integer transportationId);
}
