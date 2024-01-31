package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.usecases.AppointmentService;
import com.nfb.modules.companies.core.usecases.QRCodeGenerator;
import com.nfb.modules.companies.core.usecases.QRCodeService;
import com.nfb.modules.companies.core.usecases.WorkingDayService;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    private RegisteredUserService registeredUserService;



    public AppointmentController(AppointmentService appointmentService,WorkingDayService workingDayService, QRCodeService qrCodeService, RegisteredUserService registeredUserService) {
        this.appointmentService = appointmentService;
        this.workingDayService = workingDayService;
        this.qrCodeService = qrCodeService;
        this.registeredUserService = registeredUserService;
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

    @PutMapping("/download-equipment/{appointmentId}/{qrCodeId}")
    public long downloadEquipment(@PathVariable long appointmentId, @PathVariable long qrCodeId) {
        return appointmentService.downloadEquipment(appointmentId, qrCodeId);
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

    @GetMapping("/find-downloaded-appointments")
    public ResponseEntity<List<AppointmentDto>> findDownloadedAppointments() {

        List<Appointment> appointments = appointmentService.findDownloadedAppointments();

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/find-expired-appointments")
    public ResponseEntity<List<AppointmentDto>> findExpiredAppointments() {

        List<Appointment> appointments = appointmentService.findExpiredAppointments();

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/find-non-expired-not-downloaded-appointments")
    public ResponseEntity<List<AppointmentDto>> findNonExpiredNotDownloadedAppointments() {

        List<Appointment> appointments = appointmentService.findNonExpiredNotDownloadedAppointments();

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/get-all-from-calendar/{id}")
    public ResponseEntity<List<AppointmentDto>> findByCompany(@PathVariable long id) {
        List<Appointment> appointments = appointmentService.getAllAvailable();
        List<WorkingDay> days = workingDayService.getByWorkingCalendarId(id);

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            if(days.stream().anyMatch(item -> a.getWorkingDay().getId() ==item.getId() ) ){
                if(a.getPickUpDate().isAfter(LocalDateTime.now()) &&
                        a.getType() != AppointmentType.Extraordinary)
                {
                    dtos.add(new AppointmentDto(a));
                }
            }

        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
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

    @GetMapping("/get-all-qr")
    public ResponseEntity<List<QRCodeDto>> getAllQR() {
        List<QRCode> qrCodes = qrCodeService.getAll();

        List<QRCodeDto> dtos = new ArrayList<>();
        for (QRCode qrCode : qrCodes) {
            dtos.add(new QRCodeDto(qrCode));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<QRCodeDto> addQRCode(@RequestBody QRCodeDto qrCodeDto) {
        if(qrCodeDto.getReservedEquipment().isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Appointment appointment = appointmentService.getById(qrCodeDto.getId());
        QRCode addedQRCode = qrCodeService.addQRCodeFromDto(qrCodeDto);


        if (addedQRCode != null) {
            QRCodeDto addedQRCodeDto = new QRCodeDto(addedQRCode);
            return new ResponseEntity<>(addedQRCodeDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cancel-reservation/{id}")
    public ResponseEntity<QRCodeDto> cancelQRCode(@PathVariable long id) {
        // Retrieve the user ID from the authentication token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        RegisteredUser user = registeredUserService.getByUsername(username);

        // Now you have the user ID and can use it as needed
        QRCode canceledQRCode = qrCodeService.cancelQRCodeById(id, user);

        if (canceledQRCode != null) {
            QRCodeDto canceledQRCodeDto = new QRCodeDto(canceledQRCode);
            return new ResponseEntity<>(canceledQRCodeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
