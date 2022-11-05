package ues.edu.sv.packages_tracking.service;

import java.util.List;

import ues.edu.sv.packages_tracking.entities.Rol;

public interface RolService {
    
    public List<Rol> getAllRoles();

    public Rol getRolById(Integer idRol);
}
