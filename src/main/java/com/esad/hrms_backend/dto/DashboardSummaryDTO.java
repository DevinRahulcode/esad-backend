package com.esad.hrms_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryDTO {
    private long totalEmployees;
    private long pendingLeaves;
    private long approvedLeaves;
    private long rejectedLeaves;
    private long todayAttendance;
}
