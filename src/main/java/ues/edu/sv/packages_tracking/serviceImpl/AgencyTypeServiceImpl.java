package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.AgencyType;
import ues.edu.sv.packages_tracking.repository.TipoAgenciaRepository;
import ues.edu.sv.packages_tracking.service.AgencyTypeService;

@Service
public class AgencyTypeServiceImpl implements AgencyTypeService{

    @Autowired
    private TipoAgenciaRepository tipoAgenciaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AgencyType> findAll() {
        List<AgencyType> list = new ArrayList<>();
        list =(List<AgencyType>) Optional.of(tipoAgenciaRepository.findAll()).orElse(Collections.emptyList());
        return list;
    }
    
}
