package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.TrackingHist;
import ues.edu.sv.packages_tracking.repository.TrackingHistRepository;
import ues.edu.sv.packages_tracking.service.TrackingHistService;

@Service
@Transactional
public class TrackingHistServiceImpl implements TrackingHistService {

    @Autowired
    TrackingHistRepository repository;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<TrackingHist> findAllByPackaeId(String packageId) {
        List<TrackingHist> history = new ArrayList<>();
        try {
            history = repository.findByPackageId(packageId);
            if(!history.isEmpty())
                return history;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener historico por ID del paquete", e);
        }
        return history;
    }

    @Override
    public TrackingHist findById(Integer id) {
        TrackingHist trackingHist = new TrackingHist();
        
        try {
            trackingHist = repository.findById(id).orElse(null);
            if(trackingHist!=null)
                return trackingHist;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener hitorico por id", e);
        }
        return trackingHist;
    }

    @Override
    public void save(TrackingHist trackingHist) {
        try {
            if (trackingHist!=null) {
                repository.save(trackingHist);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar historial", e);
        }
    }
    
}
