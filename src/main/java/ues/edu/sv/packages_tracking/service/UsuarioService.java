package ues.edu.sv.packages_tracking.service;

import java.util.List;


import ues.edu.sv.packages_tracking.entities.Users;

public interface UsuarioService {
    
    /**
     * 
     * @return retorna una lista de todos los usuarios
     */
    public List<Users> findAll();

    /**
     * Persiste o actualiza un usuario en la base de datos
     * @param user
     */
    public void guardarUsuario(Users user);

    /**
     * Retorna un usuario deacuerdo a su ID
     * @param idUsuario
     * @return
     */
    public Users obtenerPorId(Integer idUsuario);

    public Users getByUsername(String username);
}
