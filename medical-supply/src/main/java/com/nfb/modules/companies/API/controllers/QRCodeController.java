package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.usecases.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/qr-codes")
@CrossOrigin
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<QRCodeDto>> getAll() {

        List<QRCode> qrCodes = qrCodeService.getAll();

        List<QRCodeDto> dtos = new ArrayList<>();
        for (QRCode q : qrCodes) {
            dtos.add(new QRCodeDto(q));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
