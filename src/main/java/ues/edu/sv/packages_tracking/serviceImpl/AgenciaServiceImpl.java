package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Agency;
import ues.edu.sv.packages_tracking.repository.AgenciaRepository;
import ues.edu.sv.packages_tracking.service.AgenciaService;

@Service
@Transactional
public class AgenciaServiceImpl implements AgenciaService{
    @Autowired
    private AgenciaRepository agenciaRepository;
    
    @Override
    public List<Agency> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void guardarAgencia(Agency agency) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Agency obtenerPorId(Integer idAgencia) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
