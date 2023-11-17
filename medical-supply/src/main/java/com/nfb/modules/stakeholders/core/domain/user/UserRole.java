package com.nfb.modules.stakeholders.core.domain.user;
import com.fasterxml.jackson.annotation.JsonFormat;
public enum UserRole {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    RegisteredUser,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    SystemAdministrator,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    CompanyAdministrator
}
