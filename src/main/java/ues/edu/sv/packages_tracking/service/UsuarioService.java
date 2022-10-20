package ues.edu.sv.packages_tracking.service;

import java.util.List;

import ues.edu.sv.packages_tracking.entities.User;

public interface UsuarioService {
    
    /**
     * 
     * @return retorna una lista de todos los usuarios
     */
    public List<User> findAll();

    /**
     * Persiste o actualiza un usuario en la base de datos
     * @param user
     */
    public void guardarUsuario(User user);

    /**
     * Retorna un usuario deacuerdo a su ID
     * @param idUsuario
     * @return
     */
    public User obtenerPorId(Integer idUsuario);
}
