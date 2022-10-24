package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.User;
import ues.edu.sv.packages_tracking.repository.UsuarioRepository;
import ues.edu.sv.packages_tracking.service.UsuarioService;

@Service("usuarioservice")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    //@Qualifier("usuariorepository")
    private UsuarioRepository userRepository;


    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public void guardarAgencia(User user) {
        
        
        
    }

    @Override
    public User obtenerPorId(Integer idUsuario) {
        
        return null;
    }
    
}
