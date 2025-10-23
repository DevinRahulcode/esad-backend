package com.esad.hrms_backend.controller;

import com.esad.hrms_backend.dto.RecentActivityDTO;
import com.esad.hrms_backend.service.RecentActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recent-activity")
@CrossOrigin("*")
public class RecentActivityController {

    private final RecentActivityService recentActivityService;

    public RecentActivityController(RecentActivityService recentActivityService) {
        this.recentActivityService = recentActivityService;
    }

    @GetMapping
    public List<RecentActivityDTO> getAllRecentActivities() {
        return recentActivityService.getRecentActivities();
    }
}
