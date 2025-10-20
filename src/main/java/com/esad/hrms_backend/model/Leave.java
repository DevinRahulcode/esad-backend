package com.esad.hrms_backend.model;

import com.esad.hrms_backend.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`leave_table`")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate leaveDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;

    @ManyToOne(fetch = FetchType.EAGER) // Force to load employee with each leave
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference // Prevent recursion loop when serializing
    private Employee employee;
}
