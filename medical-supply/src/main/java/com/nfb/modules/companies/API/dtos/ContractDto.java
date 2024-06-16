package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    @Schema(description = "User ID")
    private long userId;
    @Schema(description = "Company ID")
    private long companyId;
    @Schema(description = "Equipment ID")
    private long equipmentId;
    @Schema(description = "Quantity ID")
    private int quantity;
    @Schema(description = "Pick up date ID")
    private LocalDateTime pickupDate;
}
