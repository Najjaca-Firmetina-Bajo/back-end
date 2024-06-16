package com.nfb.modules.companies.core.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum QRStatus {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    NEW,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    PROCESSED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    DECLINED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    CANCELED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    EXPIRED
}
