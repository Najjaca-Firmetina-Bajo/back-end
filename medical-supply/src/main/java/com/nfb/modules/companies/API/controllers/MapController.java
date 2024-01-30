package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.EquipmentDto;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/simulator")
@CrossOrigin
public class MapController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public MapController() {

    }

    @GetMapping("/test")
    public void getAll() {

        broadcastNotification("cao");
    }
    @MessageMapping("/send/message")
    public String broadcastNotification(String message) {
        String messageConverted = message;

        this.simpMessagingTemplate.convertAndSend("/socket-publisher", message);

        return message;
    }

}
