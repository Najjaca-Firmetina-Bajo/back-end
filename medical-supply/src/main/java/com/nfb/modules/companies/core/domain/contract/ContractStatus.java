package com.nfb.modules.companies.core.domain.contract;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum ContractStatus {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    VALID,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    DELIVERED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    EXPIRED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    INVALID
}
