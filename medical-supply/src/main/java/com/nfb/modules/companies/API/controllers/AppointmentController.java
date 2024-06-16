package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.API.dtos.CreateAppointmentDto;
import com.nfb.modules.companies.API.dtos.EquipmentQuantityDto;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.*;
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
import java.util.Comparator;
import java.util.Date;
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
        Appointment appointment = appointmentService.getById(qrCodeDto.getAppointmentId());
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

    @GetMapping("/extraordinary-appointments")
    public ResponseEntity<List<AppointmentDto>> getExtraordinaryAppointments(@RequestParam Date date, @RequestParam long companyId) {
        List<Appointment> appointments = appointmentService.createIfCompanyIsWorking(date, companyId);
        return getListResponseEntity(appointments);
    }

    @GetMapping("/get-users-downloaded-appointments/{userId}")
    public ResponseEntity<List<AppointmentDto>> getUsersDownloadedAppointments(@PathVariable long userId){
        List<QRCode> qrCodes = qrCodeService.getProcessedByUserId(userId);

        List<AppointmentDto> dtos = new ArrayList<>();

        for (QRCode q :
                qrCodes) {
            AppointmentDto a = new AppointmentDto(q.getAppointment());
            List<QREquipment> equipments = q.getReservedEquipment();
            for (QREquipment e :
                    equipments) {
                List<EquipmentQuantityDto> eqdtoList = new ArrayList<>();
                eqdtoList.add(new EquipmentQuantityDto(e.getEquipmentId(), e.getQuantity()));
                a.setReservedEquipment(eqdtoList);
            }
            dtos.add(a);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/sort/{ascOrDesc}/{type}/{userId}")
    public ResponseEntity<List<AppointmentDto>> sort(@PathVariable String ascOrDesc, @PathVariable String type, @PathVariable long userId){
        List<QRCode> qrCodes = qrCodeService.getProcessedByUserId(userId);
        List<AppointmentDto> dtos = getAppointmentDtos(qrCodes);

        // Definisanje Comparator-a
        Comparator<AppointmentDto> comparator = null;
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            if (type.equalsIgnoreCase("date")) {
                comparator = Comparator.comparing(AppointmentDto::getPickUpDate);
            } else {
                comparator = Comparator.comparing(AppointmentDto::getDuration);
            }
        } else {
            if (type.equalsIgnoreCase("date")) {
                comparator = Comparator.comparing(AppointmentDto::getPickUpDate).reversed();
            } else {
                comparator = Comparator.comparing(AppointmentDto::getDuration).reversed();
            }
        }

        // Sortiranje liste dtos
        dtos.sort(comparator);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean isDeleted = appointmentService.delete(id);

        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Appointment cannot be deleted because of existing reservations."); // Vraćamo 400 Bad Request ako brisanje nije uspelo
        }
    }

    @PutMapping("/pick-up-reservation/{appointmentId}/{qrEquipmentId}")
    public ResponseEntity<Boolean> pickUpReservation(@PathVariable Long appointmentId, @PathVariable Long qrEquipmentId) {
        boolean isPickedUp = appointmentService.pickUpEquipmentWithoutQRCode(appointmentId, qrEquipmentId);

        if(isPickedUp){
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }


    private ResponseEntity<List<AppointmentDto>> getListResponseEntity(List<Appointment> appointments) {
        List<AppointmentDto> dtos = new ArrayList<>();

        if (appointments == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Company not working on that day
        } else {
            for (Appointment a :
                    appointments) {
                dtos.add(new AppointmentDto(a));
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK); // Return the list of appointments
        }
    }

    @GetMapping("/get-users-new-appointments/{userId}")
    public ResponseEntity<List<AppointmentDto>> getUsersNewAppointments(@PathVariable long userId){
        List<QRCode> qrCodes = qrCodeService.getNewByUserId(userId);

        List<AppointmentDto> dtos = getAppointmentDtos(qrCodes);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private static List<AppointmentDto> getAppointmentDtos(List<QRCode> qrCodes) {
        List<AppointmentDto> dtos = new ArrayList<>();

        for (QRCode q :
                qrCodes) {
            AppointmentDto a = new AppointmentDto(q.getAppointment());
            List<QREquipment> equipments = q.getReservedEquipment();
            for (QREquipment e :
                    equipments) {
                List<EquipmentQuantityDto> eqdtoList = new ArrayList<>();
                eqdtoList.add(new EquipmentQuantityDto(e.getEquipmentId(), e.getQuantity()));
                a.setReservedEquipment(eqdtoList);
                a.setQrCodeId(q.getId());
            }
            dtos.add(a);
        }
        return dtos;
    }



}
