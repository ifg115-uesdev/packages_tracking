package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.StatePackage;
import ues.edu.sv.packages_tracking.repository.StatePackageRepository;
import ues.edu.sv.packages_tracking.service.StatePackageService;

@Service
@Transactional
public class StatePackageServiceImpl implements StatePackageService{

    @Autowired
    private StatePackageRepository packageRepository;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<StatePackage> getAllStates() {
        List<StatePackage> statesList = new ArrayList<>();
        try {
            statesList = packageRepository.findAll();
            if (!statesList.isEmpty()) {
                return statesList;
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener lista de estados de paquete", e);
        }
        return statesList;
    }

    @Override
    public Optional<StatePackage> getOneById(Integer id) {
        Optional<StatePackage> statePackage = null;
        try {
            statePackage = packageRepository.findById(id);
            if (statePackage!=null) {
                return statePackage;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener un estado por ID", e);
        }
        return statePackage;
    }
    
}
