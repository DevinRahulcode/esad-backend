package com.esad.hrms_backend.repository;

import com.esad.hrms_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
