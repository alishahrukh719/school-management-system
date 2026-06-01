package com.example.studentapi;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "message", "Student API is running",
                "students", "/api/students",
                "frontend", "/app",
                "portfolio", "/portfolio",
                "sampleStudent", "/api/students/sample-id");
    }
}
