package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.ProfitQueryDto;
import com.nfb.modules.companies.core.usecases.AnalyticsService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @GetMapping("/get-reservation-count-by-year/{companyId}")
    public ResponseEntity<Map<Integer, Long>> findReservationsByYear(@PathVariable long companyId) {
        Map<Integer, Long> appointmentsByYear = analyticsService.getQRCodeCountByYear(companyId);
        return ResponseEntity.ok(appointmentsByYear);
    }

    @GetMapping("/get-reservation-count-by-quarter/{companyId}/{year}")
    public ResponseEntity<Map<Integer, Long>> findReservationsByQuarter(@PathVariable long companyId, @PathVariable int year) {
        Map<Integer, Long> appointmentsByYear = analyticsService.getQRCodeCountByQuarter(companyId, year);
        return ResponseEntity.ok(appointmentsByYear);
    }

    @GetMapping("/get-reservation-count-by-month/{companyId}/{year}")
    public ResponseEntity<Map<Integer, Long>> findReservationsByMonth(@PathVariable long companyId, @PathVariable int year) {
        Map<Integer, Long> appointmentsByMonth = analyticsService.getQRCodeCountByMonth(companyId, year);
        return ResponseEntity.ok(appointmentsByMonth);
    }

    @GetMapping("/get-profit/{companyId}/{startDate}/{endDate}")
    public ResponseEntity<Double> getProfit(@PathVariable long companyId, @PathVariable String startDate, @PathVariable String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

        double profit = analyticsService.countIncomeForPeriod(companyId, startDateTime, endDateTime);

        return ResponseEntity.ok(profit);
    }
}
