package com.esad.hrms_backend.model;


import com.esad.hrms_backend.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String nic;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
