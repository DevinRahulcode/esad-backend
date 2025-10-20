package com.esad.hrms_backend.controller;

import com.esad.hrms_backend.exception.RecordNotFoundException;
import com.esad.hrms_backend.model.Employee;
import com.esad.hrms_backend.repository.EmployeeRepository;
import com.esad.hrms_backend.service.EmployeeService; // Import EmployeeService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    // --- FIX: ADDED THIS ENDPOINT TO GET A SINGLE EMPLOYEE BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/by-email")
    public Employee getEmployeeByEmail(@RequestParam String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/getall")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable long id, @RequestBody Employee editEmployeeDetails) {
        Employee editEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"));

        editEmployee.setName(editEmployeeDetails.getName());
        editEmployee.setAddress(editEmployeeDetails.getAddress());
        editEmployee.setNic(editEmployeeDetails.getNic());
        editEmployee.setEmail(editEmployeeDetails.getEmail());
        editEmployee.setGender(editEmployeeDetails.getGender());
        // We don't update the image here, that's handled by upload-image
        employeeRepository.save(editEmployee);
        return ResponseEntity.ok(editEmployee);
    }

    @PostMapping("/upload-image/{id}")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Employee not found"));
            employee.setProfileImage(file.getBytes());
            employeeRepository.save(employee);
            return ResponseEntity.ok("Profile image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));
        return ResponseEntity.ok(employee.getProfileImage());
    }
}