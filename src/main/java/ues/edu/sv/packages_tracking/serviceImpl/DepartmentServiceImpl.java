package ues.edu.sv.packages_tracking.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.edu.sv.packages_tracking.entities.Department;
import ues.edu.sv.packages_tracking.repository.DepartamentoRepository;
import ues.edu.sv.packages_tracking.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        
        List<Department> list = new ArrayList<>();
        list =(List<Department>) Optional.of(departamentoRepository.findAll()).orElse(Collections.emptyList());
        return list;
    }
    
}
