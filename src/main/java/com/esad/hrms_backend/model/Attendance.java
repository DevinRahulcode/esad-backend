package com.esad.hrms_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique id of employee (could be username or employee number)
    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private String employeeName;

    // e.g., "CHECKIN", "CHECKOUT", "LEAVE"
    @Column(nullable = false)
    private String type;

    // Optional reason / note for leave
    @Column(length = 1000)
    private String note;

    // When this record happened / was created
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Attendance() {}

    public Attendance(String employeeId, String employeeName, String type, String note, LocalDateTime timestamp) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.type = type;
        this.note = note;
        this.timestamp = timestamp;
    }

    // Getters and setters

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
}
