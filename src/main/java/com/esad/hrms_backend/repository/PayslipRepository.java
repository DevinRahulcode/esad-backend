package com.esad.hrms_backend.repository;

import com.esad.hrms_backend.model.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, Long> {
    List<Payslip> findByEmployeeId(Long employeeId);
}
