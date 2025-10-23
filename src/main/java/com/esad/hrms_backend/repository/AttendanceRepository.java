package com.esad.hrms_backend.repository;


import com.esad.hrms_backend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // find history for an employee, newest first
    List<Attendance> findByEmployeeIdOrderByTimestampDesc(String employeeId);
    List<Attendance> findByEmployeeId(String employeeId);

}