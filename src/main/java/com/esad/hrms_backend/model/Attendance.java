package com.esad.hrms_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private String type;

    @Column(length = 1000)
    private String note;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // 🆕 New field: store location name (e.g. "Colombo, Sri Lanka")
    private String location;

    public Attendance() {}

    public Attendance(String employeeId, String employeeName, String type, String note, LocalDateTime timestamp, String location) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.type = type;
        this.note = note;
        this.timestamp = timestamp;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
