package com.esad.hrms_backend.dto;

import com.esad.hrms_backend.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveDTO {
    private Long id;
    private String employeeId;     // Add employee ID
    private String employeeName;   // Flat field
    private LocalDate leaveDate;
    private String reason;
    private LeaveStatus status;
}
