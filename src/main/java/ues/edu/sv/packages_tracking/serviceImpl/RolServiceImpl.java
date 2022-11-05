package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Rol;
import ues.edu.sv.packages_tracking.repository.RolRepository;
import ues.edu.sv.packages_tracking.service.RolService;

@Service
@Transactional
public class RolServiceImpl implements RolService{

    @Autowired
    RolRepository repository;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<Rol> getAllRoles() {
        List<Rol> roles = new ArrayList<>();
        try {
            roles = repository.findAll();
            if (!roles.isEmpty())
                return roles;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener la lista de roles", e);
        }
        return roles;
    }

    @Override
    public Rol getRolById(Integer idRol) {
        Rol rol=null;
        try {
            rol=repository.findById(idRol).get();
            if(rol!=null)
                return rol;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener un rol por ID", e);
        }
        return rol;
    }
    
}
