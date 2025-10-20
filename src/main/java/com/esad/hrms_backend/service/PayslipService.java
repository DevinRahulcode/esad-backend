package com.esad.hrms_backend.service;

import com.esad.hrms_backend.model.Employee;
import com.esad.hrms_backend.model.Payslip;
import com.esad.hrms_backend.repository.PayslipRepository;
import com.esad.hrms_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PayslipService {

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Payslip uploadPayslip(Long employeeId, MultipartFile file) throws IOException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        Payslip payslip = new Payslip();
        payslip.setFilename(file.getOriginalFilename());
        payslip.setFileType(file.getContentType());
        payslip.setData(file.getBytes());
        payslip.setEmployee(employee);

        return payslipRepository.save(payslip);
    }

    public List<Payslip> getPayslipsByEmployee(Long employeeId) {
        return payslipRepository.findByEmployeeId(employeeId);
    }

    // New method for downloading a single payslip by ID
    public Payslip getPayslipById(Long payslipId) {
        Optional<Payslip> optionalPayslip = payslipRepository.findById(payslipId);
        return optionalPayslip.orElse(null);
    }
}
