package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Package;
import ues.edu.sv.packages_tracking.repository.PackageRepository;
import ues.edu.sv.packages_tracking.service.PackageService;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    @Autowired
    PackageRepository repository;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Package findById(String id) {
        Package paquete = new Package();
        try {
            paquete = repository.findById(id).orElse(null);
            if (paquete != null)
                return paquete;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener un paquete por ID", e);
        }
        return paquete;
    }

    @Override
    public void savePackage(Package paquete) {
        try {
            if (paquete != null) {
                String idPackage = RandomStringUtils.randomAlphanumeric(50);
                while (repository.existsById(idPackage)) {
                    idPackage = RandomStringUtils.randomAlphanumeric(50);
                }
                paquete.setPackageId(idPackage);
                repository.save(paquete);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar paquete", e);
        }

    }

    @Override
    public List<Package> findByTransportationId(Integer transportationId) {
        return Optional.of(repository.findByTransportationId(transportationId)).orElse(Collections.emptyList());
    }

}
