package ues.edu.sv.packages_tracking.service;

import java.util.List;

import ues.edu.sv.packages_tracking.entities.Agency;

public interface AgenciaService {
    
    /**
     * 
     * @return retorna una lista de todas las agencias
     */
    public List<Agency> findAll();

    /**
     * Persiste o actualiza una agencia en la base de datos
     * @param agency
     */
    public void guardarAgencia(Agency agency);

    /**
     * Retorna una agencia deacuerdo a su ID
     * @param idAgencia
     * @return
     */
    public Agency obtenerPorId(Integer idAgencia);


}
