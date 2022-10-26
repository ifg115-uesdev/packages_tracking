package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Transportation;
import ues.edu.sv.packages_tracking.repository.TransportationRepository;
import ues.edu.sv.packages_tracking.service.TransportationService;

@Service
@Transactional
public class TransportationServiceImpl implements TransportationService {

    @Autowired
    TransportationRepository repository;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<Transportation> getAllTransportation() {
        List<Transportation> list = new ArrayList<>();
        try {
            list = repository.findAll();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener todos los transportes", e);
        }
        return list;
    }

    @Override
    public Optional<Transportation> getOneById(Integer id) {
        Optional<Transportation> transportation = null;
        try {
            transportation = repository.findById(id);

            if (transportation != null) {
                return transportation;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener transporte por ID", e);
        }
        return transportation;
    }

    @Override
    public void saveTransportation(Transportation transportation) {
        try {
            if (transportation!=null) {
                repository.save(transportation);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al persitir transporte en la DB", e);
        }
        
    }
    
}
