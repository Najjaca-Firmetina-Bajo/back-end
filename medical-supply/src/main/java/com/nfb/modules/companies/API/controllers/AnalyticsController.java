package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.core.usecases.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/get-appointment-count-by-year/{companyId}")
    public ResponseEntity<Map<Integer, Long>> findByYear(@PathVariable long companyId) {
        Map<Integer, Long> appointmentsByYear = analyticsService.getAppointmentCountByYear(companyId);
        return ResponseEntity.ok(appointmentsByYear);
    }

    @GetMapping("/get-appointment-count-by-quarter/{companyId}/{year}")
    public ResponseEntity<Map<Integer, Long>> findByQuarter(@PathVariable long companyId, @PathVariable int year) {
        Map<Integer, Long> appointmentsByQuarter = analyticsService.getAppointmentCountByQuarter(companyId, year);
        return ResponseEntity.ok(appointmentsByQuarter);
    }

    @GetMapping("/get-appointment-count-by-month/{companyId}/{year}")
    public ResponseEntity<Map<Integer, Long>> findByMonth(@PathVariable long companyId, @PathVariable int year) {
        Map<Integer, Long> appointmentsByMonth = analyticsService.getAppointmentCountByMonth(companyId, year);
        return ResponseEntity.ok(appointmentsByMonth);
    }
}
