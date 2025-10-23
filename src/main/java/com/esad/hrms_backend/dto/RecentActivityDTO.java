package com.esad.hrms_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RecentActivityDTO {
    private String type;           // Attendance, Leave, Payslip
    private String employeeId;
    private String employeeName;

    private String details;        // e.g., "Checked in at Colombo"
    private LocalDateTime timestamp;
}
