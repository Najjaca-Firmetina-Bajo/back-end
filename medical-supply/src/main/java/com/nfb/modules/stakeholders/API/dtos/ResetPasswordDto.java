package com.nfb.modules.stakeholders.API.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResetPasswordDto {
    @Schema(description = "User ID")
    private long id;
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Old password")
    private String oldPassword;
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "New password")
    private String newPassword;
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Confirmation new password")
    private String confirmationNewPassword;
}
