package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfitQueryDto {
    @Schema(description = "Company ID")
    private long id;
    @Schema(description = "Start date")
    private LocalDateTime startDate;
    @Schema(description = "End date")
    private LocalDateTime endDate;
}
