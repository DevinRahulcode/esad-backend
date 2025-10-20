package com.esad.hrms_backend.repository;

import com.esad.hrms_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Import this

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // --- ADD THIS METHOD ---
    Optional<Employee> findByEmail(String email);
}