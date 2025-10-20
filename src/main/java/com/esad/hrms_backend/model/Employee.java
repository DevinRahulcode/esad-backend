package com.esad.hrms_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.esad.hrms_backend.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @Lob
    private byte[] profileImage;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Leave> leaves;
}
