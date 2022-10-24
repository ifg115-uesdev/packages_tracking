package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.User;
import ues.edu.sv.packages_tracking.repository.UsuarioRepository;
import ues.edu.sv.packages_tracking.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService  {
     
     @Autowired
     private UsuarioRepository repositorio;

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void guardarAgencia(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User obtenerPorId(Integer idUsuario) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
