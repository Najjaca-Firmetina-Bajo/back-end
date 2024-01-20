package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.AppointmentService;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.companies.core.usecases.QRCodeGenerator;
import com.nfb.modules.companies.core.usecases.WorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private WorkingDayService workingDayService;


    public AppointmentController(AppointmentService appointmentService,WorkingDayService workingDayService) {
        this.appointmentService = appointmentService;
        this.workingDayService = workingDayService;
    }

    @GetMapping("/findBy/{workingDayId}")
    public ResponseEntity<List<AppointmentDto>> findBy(@PathVariable long workingDayId) {

        List<Appointment> appointments = appointmentService.getBy(workingDayId);

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AppointmentDto>> findBy() {

        List<Appointment> appointments = appointmentService.getAll();

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



    @GetMapping("/get-all-calendar/{id}")
    public ResponseEntity<List<AppointmentDto>> findByCompany(@PathVariable long id) {
        /*
        List<Appointment> appointments = appointmentService.getAll();
        List<WorkingDay> days = workingDayService.getBy(id);

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            if(days.stream().anyMatch(item -> a.getWorkingDay().getId() ==item.getId() ) ){
                if(a.getPickUpDate().isAfter(LocalDateTime.now()) && a.getType() != AppointmentType.Extraordinary && a.getRegisteredUser() == null){
                    dtos.add(new AppointmentDto(a));
                }
            }

        }
        */
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<AppointmentDto> reserve(@RequestBody AppointmentDto appointmentDto) throws Exception {

        var app = appointmentService.updateAppointment(appointmentDto);
        return new ResponseEntity<>(new AppointmentDto(app), HttpStatus.OK);
    }

    @GetMapping(value = "/generate/{appointmentId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCodeImage(@PathVariable long appointmentId) {
        try {
            // Convert the appointment ID to a string
            String data = String.valueOf(appointmentId);

            // Assuming you have a utility method to generate the QR code image
            byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(data, 200, 200);

            // Return the byte array as the image response
            return ResponseEntity.ok().body(qrCodeImage);
        } catch (Exception e) {
            // Handle exceptions, e.g., appointment not found
            return ResponseEntity.status(500).build();
        }
    }

}
