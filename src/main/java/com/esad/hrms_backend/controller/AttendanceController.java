package com.esad.hrms_backend.controller;

import com.esad.hrms_backend.model.Attendance;
import com.esad.hrms_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<?> getAttendanceHistory(@RequestParam String employeeId) {
        try {
            return ResponseEntity.ok(attendanceService.getHistoryByEmployeeId(employeeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error fetching history");
        }
    }

    // ✅ New endpoint to fetch all records
    @GetMapping("/all")
    public ResponseEntity<?> getAllAttendance() {
        try {
            return ResponseEntity.ok(attendanceService.getAllAttendance());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error fetching all attendance");
        }
    }

    @PostMapping
    public ResponseEntity<?> createAttendance(@RequestBody Map<String, Object> body) {
        try {
            String employeeId = (String) body.get("employeeId");
            String employeeName = (String) body.getOrDefault("employeeName", "");
            String type = (String) body.get("type");
            String note = (String) body.getOrDefault("note", "");
            Double latitude = body.get("latitude") != null ? Double.parseDouble(body.get("latitude").toString()) : null;
            Double longitude = body.get("longitude") != null ? Double.parseDouble(body.get("longitude").toString()) : null;

            if (employeeId == null || type == null || latitude == null || longitude == null) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }

            Attendance attendance = attendanceService.createAttendance(employeeId, employeeName, type, note, latitude, longitude);
            return ResponseEntity.ok(attendance);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error saving attendance");
        }
    }
}
