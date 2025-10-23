package com.esad.hrms_backend.service;

import com.esad.hrms_backend.model.Attendance;
import com.esad.hrms_backend.repository.AttendanceRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getHistoryByEmployeeId(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    // ✅ New method to fetch all attendance records
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Helper method: convert lat/lon to location name
    public String getLocationName(double latitude, double longitude) {
        try {
            String url = String.format("https://nominatim.openstreetmap.org/reverse?lat=%s&lon=%s&format=json", latitude, longitude);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SpringBootApp");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            return json.has("display_name") ? json.getString("display_name") : "Unknown Location";
        } catch (Exception e) {
            e.printStackTrace();
            return "Location not available";
        }
    }

    // Create attendance record
    public Attendance createAttendance(String employeeId, String employeeName, String type, String note, Double latitude, Double longitude) {
        String locationName = getLocationName(latitude, longitude);
        Attendance attendance = new Attendance(employeeId, employeeName, type, note, LocalDateTime.now(), locationName);
        return attendanceRepository.save(attendance);
    }
}
