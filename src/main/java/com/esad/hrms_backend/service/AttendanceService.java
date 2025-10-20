package com.esad.hrms_backend.service;


import com.esad.hrms_backend.model.Attendance;
import com.esad.hrms_backend.repository.AttendanceRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance createAttendance(String employeeId, String employeeName, String type, String note) {
        Attendance a = new Attendance(employeeId, employeeName, type, note, LocalDateTime.now());
        return attendanceRepository.save(a);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }

    public List<Attendance> getHistory(String employeeId) {
        return attendanceRepository.findByEmployeeIdOrderByTimestampDesc(employeeId);
    }
}
