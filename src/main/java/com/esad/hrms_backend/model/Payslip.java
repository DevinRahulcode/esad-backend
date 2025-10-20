package com.esad.hrms_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payslip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String filename;

    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;
}
