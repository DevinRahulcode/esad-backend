package com.esad.hrms_backend.service;

import com.esad.hrms_backend.dto.RecentActivityDTO;
import com.esad.hrms_backend.model.Attendance;
import com.esad.hrms_backend.model.Leave;
import com.esad.hrms_backend.model.Payslip;
import com.esad.hrms_backend.repository.AttendanceRepository;
import com.esad.hrms_backend.repository.LeaveRepository;
import com.esad.hrms_backend.repository.PayslipRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecentActivityService {

    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PayslipRepository payslipRepository;

    public RecentActivityService(AttendanceRepository attendanceRepository,
                                 LeaveRepository leaveRepository,
                                 PayslipRepository payslipRepository) {
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
        this.payslipRepository = payslipRepository;
    }

    public List<RecentActivityDTO> getRecentActivities() {
        List<RecentActivityDTO> activities = new ArrayList<>();

        // Attendance
        for (Attendance a : attendanceRepository.findAll()) {
            activities.add(new RecentActivityDTO(
                    "Attendance",
                    a.getEmployeeId(),
                    a.getEmployeeName(),
                    a.getType() + " at " + a.getLocation(),
                    a.getTimestamp()
            ));
        }

        // Leave
        for (Leave l : leaveRepository.findAll()) {
            activities.add(new RecentActivityDTO(
                    "Leave",
                    l.getEmployee().getId().toString(),
                    l.getEmployee().getName(),
                    "Leave " + l.getStatus() + ": " + l.getReason(),
                    l.getLeaveDate().atStartOfDay()
            ));
        }

        // Payslip
        for (Payslip p : payslipRepository.findAll()) {
            activities.add(new RecentActivityDTO(
                    "Payslip",
                    p.getEmployee().getId().toString(),
                    p.getEmployee().getName(),
                    "Payslip uploaded: " + p.getFilename(),
                    null
            ));
        }

        // Sort by timestamp descending
        activities.sort((a1, a2) -> {
            if (a1.getTimestamp() == null) return 1;
            if (a2.getTimestamp() == null) return -1;
            return a2.getTimestamp().compareTo(a1.getTimestamp());
        });

        // Return only the latest 5 activities
        return activities.size() > 5 ? activities.subList(0, 5) : activities;
    }

}
