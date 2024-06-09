package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.stakeholders.API.dtos.AdminInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoDto {
    @Schema(description = "Company ID")
    private long id;
    @Schema(description = "Company name")
    private String name;
    @Schema(description = "Company address")
    private String address;
    @Schema(description = "Company description")
    private String description;
    @Schema(description = "Company rating")
    private double averageRating;
    @Schema(description = "Available appointments of company")
    private List<AppointmentInfoDto> availableAppointments;
    @Schema(description = "Administrators of company")
    private List<AdminInfoDto> admins;
    @Schema(description = "Company equipments")
    private List<EquipmentInfoDto> equipments;
}
