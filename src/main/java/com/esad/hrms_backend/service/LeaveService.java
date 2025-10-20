package com.esad.hrms_backend.service;

import com.esad.hrms_backend.dto.LeaveDTO;
import com.esad.hrms_backend.enums.LeaveStatus;
import com.esad.hrms_backend.model.Employee;
import com.esad.hrms_backend.model.Leave;
import com.esad.hrms_backend.repository.EmployeeRepository;
import com.esad.hrms_backend.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    public Map<String, Integer> getLeaveCountsByEmployee(Long employeeId) {
        List<Leave> leaves = leaveRepository.findByEmployeeId(employeeId);

        long approved = leaves.stream()
                .filter(l -> l.getStatus() == LeaveStatus.APPROVED)
                .count();
        long rejected = leaves.stream()
                .filter(l -> l.getStatus() == LeaveStatus.REJECTED)
                .count();

        Map<String, Integer> counts = new HashMap<>();
        counts.put("approved", (int) approved);
        counts.put("rejected", (int) rejected);
        return counts;
    }



    public Leave requestLeave(Long employeeId, Leave leaveRequest) {
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        leaveRequest.setEmployee(emp);
        leaveRequest.setStatus(LeaveStatus.PENDING);
        return leaveRepository.save(leaveRequest);
    }

    public List<LeaveDTO> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(l -> new LeaveDTO(
                        l.getId(),
                        l.getEmployee() != null ? l.getEmployee().getId().toString() : "Unknown",
                        l.getEmployee() != null ? l.getEmployee().getName() : "Unknown",
                        l.getLeaveDate(),
                        l.getReason(),
                        l.getStatus()
                ))
                .collect(Collectors.toList());
    }

    // ✅ NEW: Fetch leave history by employee ID
    public List<LeaveDTO> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId).stream()
                .map(l -> new LeaveDTO(
                        l.getId(),
                        l.getEmployee() != null ? l.getEmployee().getId().toString() : "Unknown",
                        l.getEmployee() != null ? l.getEmployee().getName() : "Unknown",
                        l.getLeaveDate(),
                        l.getReason(),
                        l.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public Leave updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.valueOf(status.toUpperCase()));
        return leaveRepository.save(leave);
    }
}
