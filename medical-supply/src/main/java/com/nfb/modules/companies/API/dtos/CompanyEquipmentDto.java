package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class CompanyEquipmentDto {
    @Schema(description = "CompanyEquipment ID")
    private long id;
    @Schema(description = "Company ID")
    private long companyId;
    @Schema(description = "Equipment ID")
    private long equipmentId;
    @Schema(description = "Equipment quantity in company")
    private int quantity;

    public CompanyEquipmentDto(long id, long companyId, long equipmentId, int quantity) {
        this.id = id;
        this.companyId = companyId;
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public CompanyEquipmentDto(CompanyEquipment companyEquipment) {
        this.id = companyEquipment.getId();
        this.companyId = companyEquipment.getCompanyId();
        this.equipmentId = companyEquipment.getEquipmentId();
        this.quantity = companyEquipment.getQuantity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
