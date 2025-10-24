package com.esad.hrms_backend.service;

import com.esad.hrms_backend.dto.DashboardSummaryDTO;
import com.esad.hrms_backend.enums.LeaveStatus;
import com.esad.hrms_backend.model.Attendance;
import com.esad.hrms_backend.repository.AttendanceRepository;
import com.esad.hrms_backend.repository.EmployeeRepository;
import com.esad.hrms_backend.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;
    private final AttendanceRepository attendanceRepository;

    public DashboardService(EmployeeRepository employeeRepository, LeaveRepository leaveRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public DashboardSummaryDTO getDashboardSummary() {
        long totalEmployees = employeeRepository.count();

        // count leave statuses
        List<com.esad.hrms_backend.model.Leave> allLeaves = leaveRepository.findAll();
        long pendingLeaves = allLeaves.stream().filter(l -> l.getStatus() == LeaveStatus.PENDING).count();
        long approvedLeaves = allLeaves.stream().filter(l -> l.getStatus() == LeaveStatus.APPROVED).count();
        long rejectedLeaves = allLeaves.stream().filter(l -> l.getStatus() == LeaveStatus.REJECTED).count();

        // today's attendance count
        LocalDate today = LocalDate.now();
        long todayAttendance = attendanceRepository.findAll().stream()
                .filter(a -> a.getTimestamp().toLocalDate().isEqual(today))
                .count();

        return new DashboardSummaryDTO(totalEmployees, pendingLeaves, approvedLeaves, rejectedLeaves, todayAttendance);
    }
}
