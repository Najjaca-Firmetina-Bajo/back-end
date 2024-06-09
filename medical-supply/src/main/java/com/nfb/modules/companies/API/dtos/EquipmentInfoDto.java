package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentInfoDto {
    @Schema(description = "Equipment ID")
    private long id;
    @Schema(description = "Equipment name")
    private String name;
    @Schema(description = "Equipment type")
    private String type;
    @Schema(description = "Equipment description")
    private String description;
    @Schema(description = "Equipment price")
    private double price;
    @Schema(description = "Equipment quantity")
    private int quantity;
}
