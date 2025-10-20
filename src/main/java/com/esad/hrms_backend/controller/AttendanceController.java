package com.esad.hrms_backend.controller;

import com.esad.hrms_backend.model.Attendance;
import com.esad.hrms_backend.service.AttendanceService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*") // for dev: allow all origins; tighten in production
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // DTO-like request: { "employeeId": "123", "employeeName": "Devin Rahul", "type": "CHECKIN", "note": "optional" }
    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Map<String, String> body) {
        String employeeId = body.get("employeeId");
        String employeeName = body.getOrDefault("employeeName", "");
        String type = body.get("type");
        String note = body.get("note");

        if (employeeId == null || employeeId.isBlank() || type == null || type.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Attendance created = attendanceService.createAttendance(employeeId, employeeName, type, note);
        return ResponseEntity.ok(created);
    }

    // GET /api/attendance?employeeId=123
    @GetMapping
    public ResponseEntity<List<Attendance>> getHistory(@RequestParam(name = "employeeId") String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        List<Attendance> history = attendanceService.getHistory(employeeId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> allRecords = attendanceService.getAllAttendance();
        return ResponseEntity.ok(allRecords);
    }

    // optional: get by id
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getById(@PathVariable Long id) {
        return (ResponseEntity<Attendance>) attendanceService.getHistory("dummy"); // placeholder to avoid unused import
    }
}