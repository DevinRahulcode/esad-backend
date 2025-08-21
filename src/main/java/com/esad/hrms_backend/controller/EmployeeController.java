package com.esad.hrms_backend.controller;


import com.esad.hrms_backend.exception.RecordNotFoundException;
import com.esad.hrms_backend.model.Employee;
import com.esad.hrms_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/getall")
    public List<Employee> getallMembers(){
        return employeeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getmeberById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->new RecordNotFoundException("Record Not Found"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable long id, @RequestBody Employee editEmployeeDetails){
        Employee editEmployee = employeeRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record Not Found"));

        editEmployee.setName(editEmployeeDetails.getName());
        editEmployee.setAddress(editEmployeeDetails.getAddress());
        editEmployee.setNic(editEmployeeDetails.getNic());
        editEmployee.setEmail(editEmployeeDetails.getEmail());
        editEmployee.setGender(editEmployeeDetails.getGender());

        employeeRepository.save(editEmployee);
        return ResponseEntity.ok(editEmployee);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Not Found"));

    employeeRepository.delete(employee);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
