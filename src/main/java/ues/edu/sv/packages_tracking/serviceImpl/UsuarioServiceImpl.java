package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.repository.UsuarioRepository;
import ues.edu.sv.packages_tracking.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<Users> findAll() {
        List<Users> userList=new ArrayList<>();
        try {
            userList = repository.findAll();
            if (!userList.isEmpty()) {
                return userList;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener lista de usuarios", e);
        }
        return userList;
    }

    @Override
    public Users obtenerPorId(Integer idUsuario) {
        Users user = new Users();
        try {
            user= repository.findById(idUsuario).get();
            if (user.equals(null)) {
                return user;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return user;
    }

    @Override
    public void guardarUsuario(Users user) {
        try {
            if (user!=null) {
                repository.save(user);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar usuario", e);
        }
        
    }

    @Override
    public Users getByUsername(String username) {
        Users user = new Users();
        
        try {
            user = repository.findByUsername(username);
            if (user!=null) {
                return user;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erorr al obtener usuario por username", e);
        }
        return user;
    }

    
    
}
