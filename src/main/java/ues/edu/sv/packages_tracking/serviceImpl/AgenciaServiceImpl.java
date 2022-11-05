package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Agency;
import ues.edu.sv.packages_tracking.repository.AgenciaRepository;
import ues.edu.sv.packages_tracking.service.AgenciaService;

@Service
@Transactional
public class AgenciaServiceImpl implements AgenciaService {

    @Autowired
    AgenciaRepository repository;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<Agency> findAll() {
        List<Agency> agencias = new ArrayList<>();
        try {
            agencias = repository.findAll();
            if(!agencias.isEmpty())
                return agencias;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener todas las agencias", e);
        }
        return agencias;
    }

    @Override
    public void guardarAgencia(Agency agency) {
        try {
            if (agency !=null) {
                repository.save(agency);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar una agencia", e);
        }
        
    }

    @Override
    public Agency obtenerPorId(Integer idAgencia) {
        Agency agencia = new Agency();
        try {
            agencia = repository.findById(idAgencia).orElse(null);
            if (agencia !=null)
                return agencia;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener agencia por ID", e);
        }
        
        return agencia;
    }
    
}
