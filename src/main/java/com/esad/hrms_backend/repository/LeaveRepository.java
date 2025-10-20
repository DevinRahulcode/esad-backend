package com.esad.hrms_backend.repository;

import com.esad.hrms_backend.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    // Fetch all leaves for a specific employee
    List<Leave> findByEmployeeId(Long employeeId);
}
