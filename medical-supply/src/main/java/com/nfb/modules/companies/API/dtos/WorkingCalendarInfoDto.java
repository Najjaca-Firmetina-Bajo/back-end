package com.nfb.modules.companies.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingCalendarInfoDto {
    @Schema(description = "Working calendar ID")
    private long id;
    @Schema(description = "Working days of working calendar")
    private List<WorkingDayInfoDto> workingDays;
}
