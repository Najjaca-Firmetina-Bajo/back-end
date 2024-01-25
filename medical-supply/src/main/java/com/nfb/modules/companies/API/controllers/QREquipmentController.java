package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.API.dtos.QREquipmentDto;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.usecases.QREquipmentService;
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
@RequestMapping("/api/qreq")
@CrossOrigin
public class QREquipmentController {

    @Autowired
    private QREquipmentService qrEquipmentService;

    public QREquipmentController(QREquipmentService qrEquipmentService) {
        this.qrEquipmentService = qrEquipmentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<QREquipmentDto>> getAll() {

        List<QREquipment> qreq = qrEquipmentService.getAll();

        List<QREquipmentDto> dtos = new ArrayList<>();
        for (QREquipment q : qreq) {
            dtos.add(new QREquipmentDto(q));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
