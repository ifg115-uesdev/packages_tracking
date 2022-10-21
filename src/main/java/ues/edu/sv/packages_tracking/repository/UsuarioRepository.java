package ues.edu.sv.packages_tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ues.edu.sv.packages_tracking.entities.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer>{
    
}