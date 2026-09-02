package com.example.devopspulse.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopspulse.service.SystemInfoService;

@RestController
public class StatusController {

    @GetMapping("/")
    public String home() {
        return "🚀 DevOps Pulse is running!";
    }

    @GetMapping("/api/status")
    public Map<String, Object> status() {
        return Map.of(
                "application", "DevOps Pulse",
                "status", "UP",
                "version", "1.0.0",
                "timestamp", Instant.now().toString()
        );
    }
}