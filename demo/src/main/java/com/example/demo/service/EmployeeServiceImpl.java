package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;

import java.util.List;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @Override
//    public List<Employee> getSortedEmployees(){
//        return employeeRepository.findAllByOrderByLastNameAsc();
//    }

    @Override
    public List<Employee> findAll() {
        return  employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
        //.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}

