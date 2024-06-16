package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.stakeholders.API.dtos.RecipientInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractInfoDto {
    @Schema(description = "Contract ID")
    private long id;
    @Schema(description = "Contract equipment quantity ID")
    private int quantity;
    @Schema(description = "Pick up date ID")
    private LocalDateTime pickupDate;
    @Schema(description = "Contract status")
    private String status;
    @Schema(description = "Equipment info")
    private EquipmentInfoDto equipmentInfoDto;
    @Schema(description = "Recipient info")
    private RecipientInfoDto recipientInfoDto;
}
