package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingDayInfoDto {
    @Schema(description = "Working day ID")
    private long id;
    @Schema(description = "Working day date")
    private Date date;
    @Schema(description = "Working day end date")
    private Date endDate;
}
