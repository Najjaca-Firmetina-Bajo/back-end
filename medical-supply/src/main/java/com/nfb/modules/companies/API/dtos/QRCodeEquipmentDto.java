package com.nfb.modules.companies.API.dtos;

public class QRCodeEquipmentDto {
    private Long equipmentId;
    private Integer quantity;

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public QRCodeEquipmentDto(Long equipmentId, Integer quantity) {
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }
}
