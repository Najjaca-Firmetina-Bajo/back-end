package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentInfoDto {
    @Schema(description = "Appointment ID")
    private long id;
    @Schema(description = "Appointment pick up date")
    private LocalDateTime pickUpDate;
    @Schema(description = "Appointment duration")
    private int duration;
    @Schema(description = "Appointment type")
    private AppointmentType type;
    @Schema(description = "Appointment status")
    private boolean isDownloaded;
    @Schema(description = "Appointment reservation number")
    private int reservationNumber;
    @Schema(description = "Working day of appointment")
    private Long workingDayId;
    @Schema(description = "QR code of appointment")
    private Long qrCodeId;
}
