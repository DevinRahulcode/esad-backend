package com.esad.hrms_backend.controller;

import com.esad.hrms_backend.dto.LeaveDTO;
import com.esad.hrms_backend.model.Leave;
import com.esad.hrms_backend.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leave")
@CrossOrigin(origins = "*")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // ✅ Fetch all leaves
    @GetMapping("/all")
    public List<LeaveDTO> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    // ✅ Fetch leave counts for a specific employee
    // Example: GET /leave/employee/1/counts
    @GetMapping("/employee/{employeeId}/counts")
    public Map<String, Integer> getLeaveCountsByEmployee(@PathVariable Long employeeId) {
        return leaveService.getLeaveCountsByEmployee(employeeId);
    }

    // ✅ Fetch leaves by employee ID
    @GetMapping("/employee/{employeeId}")
    public List<LeaveDTO> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployee(employeeId);
    }

    // ✅ Update leave status (Admin/Manager)
    @PutMapping("/updateStatus/{leaveId}")
    public Leave updateLeaveStatus(@PathVariable Long leaveId, @RequestParam String status) {
        return leaveService.updateLeaveStatus(leaveId, status);
    }

    // ✅ Employee requests leave
    @PostMapping("/request/{employeeId}")
    public Leave requestLeave(@PathVariable Long employeeId, @RequestBody Leave leaveRequest) {
        return leaveService.requestLeave(employeeId, leaveRequest);
    }
}
