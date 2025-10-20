package com.esad.hrms_backend.controller;

import com.esad.hrms_backend.model.Payslip;
import com.esad.hrms_backend.service.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/payslips")
@CrossOrigin(origins = "*")
public class PayslipController {

    @Autowired
    private PayslipService payslipService;

    // Upload a payslip for a specific employee
    @PostMapping("/upload/{employeeId}")
    public ResponseEntity<?> uploadPayslip(@PathVariable Long employeeId,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        Payslip payslip = payslipService.uploadPayslip(employeeId, file);
        return ResponseEntity.ok(payslip);
    }

    // Fetch all payslips for a specific employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Payslip>> getPayslips(@PathVariable Long employeeId) {
        List<Payslip> payslips = payslipService.getPayslipsByEmployee(employeeId);
        return ResponseEntity.ok(payslips);
    }

    // Download payslip by payslip ID
    @GetMapping("/download/{payslipId}")
    public ResponseEntity<byte[]> downloadPayslip(@PathVariable Long payslipId) {
        Payslip payslip = payslipService.getPayslipById(payslipId);
        if (payslip == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + payslip.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(payslip.getFileType()))
                .body(payslip.getData());
    }
}
