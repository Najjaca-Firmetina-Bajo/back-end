package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.EquipmentDto;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/testmessage")
    public void getAll() {

        broadcastNotification("cao");
    }

    @GetMapping("/start-simulation")
    public void startSimulation(@RequestParam int frequency) {
        sendSimulationRequest(frequency);
    }

    private void sendSimulationRequest(int frequency) {
        // Define the URL for the HTTP request
        String apiUrl = "http://localhost:8081/api/location-sim?frequency=" + frequency;

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        // Create a request entity with the desired body
        HttpEntity<String> requestEntity = new HttpEntity<>("hello", headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send a POST request to the specified URL with the request entity
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        // Optionally, you can check the response status or body
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            System.out.println("HTTP request successful. Response: " + responseBody);
        } else {
            System.err.println("HTTP request failed. Status code: " + responseEntity.getStatusCode());
        }
    }

    @MessageMapping("/send/message")
    public String broadcastNotification(String message) {
        String messageConverted = message;

        this.simpMessagingTemplate.convertAndSend("/socket-publisher", message);

        return message;
    }

}
