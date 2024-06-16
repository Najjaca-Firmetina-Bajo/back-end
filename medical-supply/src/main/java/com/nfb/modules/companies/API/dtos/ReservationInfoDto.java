package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfoDto {
    @Schema(description = "Reservation ID")
    private long id;
    @Schema(description = "Appointment ID")
    private long appointmentId;
    @Schema(description = "QRCode ID")
    private long qrCodeId;
    @Schema(description = "Reservation ID")
    private String status;
    @Schema(description = "Pickup date")
    private LocalDateTime pickUpDate;
    @Schema(description = "Equipment ID")
    private long equipmentId;
    @Schema(description = "Equipment name")
    private String equipmentName;
    @Schema(description = "Reservation quantity")
    private int quantity;
    @Schema(description = "Equipment Recipient ID")
    private Long recipientId;
    @Schema(description = "Equipment Recipient Username")
    private String recipientUsername;
    @Schema(description = "Equipment Recipient Name")
    private String recipientName;
    @Schema(description = "Equipment Recipient Surname")
    private String recipientSurname;
}
