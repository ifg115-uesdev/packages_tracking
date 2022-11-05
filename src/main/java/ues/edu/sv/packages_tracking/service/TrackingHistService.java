package ues.edu.sv.packages_tracking.service;

import java.util.List;

import ues.edu.sv.packages_tracking.entities.TrackingHist;

public interface TrackingHistService {
    
    public List<TrackingHist> findAllByPackaeId(String packageId);

    public TrackingHist findById(Integer id);

    public void save(TrackingHist trackingHist);
}
