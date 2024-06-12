package com.nfb.modules.stakeholders.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserInfoDto {
    @Schema(description = "Registered user ID")
    private Long id;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "User name")
    private String name;
    @Schema(description = "User surname")
    private String surname;
    @Schema(description = "User's City")
    private String city;
    @Schema(description = "User's Country")
    private String country;
    @Schema(description = "User's Phone Number")
    private String phoneNumber;
}
