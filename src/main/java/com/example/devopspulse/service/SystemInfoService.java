package com.example.devopspulse.service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SystemInfoService {

    private final long startTime = System.currentTimeMillis();

    public Map<String, Object> getSystemInfo() {

        OperatingSystemMXBean osBean =
                ManagementFactory.getOperatingSystemMXBean();

        Runtime runtime = Runtime.getRuntime();

        Map<String, Object> info = new LinkedHashMap<>();

        info.put("javaVersion", System.getProperty("java.version"));
        info.put("javaVendor", System.getProperty("java.vendor"));
        info.put("osName", System.getProperty("os.name"));
        info.put("osVersion", System.getProperty("os.version"));
        info.put("osArchitecture", System.getProperty("os.arch"));
        info.put("availableProcessors", runtime.availableProcessors());

        long memoryUsed =
                runtime.totalMemory() - runtime.freeMemory();

        long memoryMax = runtime.maxMemory();

        info.put("memoryUsedMB", memoryUsed / (1024 * 1024));
        info.put("memoryMaxMB", memoryMax / (1024 * 1024));

        long uptimeSeconds =
                Duration.ofMillis(System.currentTimeMillis() - startTime)
                        .getSeconds();

        info.put("uptimeSeconds", uptimeSeconds);

        info.put("hostname",
                System.getenv().getOrDefault("HOSTNAME", "local"));

        return info;
    }
}