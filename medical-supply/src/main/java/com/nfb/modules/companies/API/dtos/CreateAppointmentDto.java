package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentDto {
    @Schema(description = "Admin ID")
    private Long adminId;
    @Schema(description = "Appointment pick up date")
    private LocalDateTime pickUpDate;
    @Schema(description = "Appointment duration")
    private int duration;
    @Schema(description = "Working day ID")
    private Long workingDayId;
}
