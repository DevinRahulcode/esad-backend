package com.esad.hrms_backend.service;

import com.esad.hrms_backend.exception.RecordNotFoundException; // Import this
import com.esad.hrms_backend.model.Employee;
import com.esad.hrms_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // --- FIX: ADDED THIS METHOD ---
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found with id: " + id));
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
    }
}