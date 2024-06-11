package com.nfb.modules.stakeholders.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCompanyLoggingDto {
    @Schema(description = "Admin ID")
    private Long id;
    @Schema(description = "User email")
    private String email;
    @Schema(description = "Is password changed")
    private boolean isPasswordChanged;
}
