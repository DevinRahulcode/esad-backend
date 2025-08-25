package com.esad.hrms_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/profile")
    public String getAdminProfile(Principal principal) {
        return "Welcome, Admin: " + principal.getName();
    }
}
