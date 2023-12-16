package com.nfb.modules.companies.core.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum AppointmentType {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Predefined,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Extraordinary
}
