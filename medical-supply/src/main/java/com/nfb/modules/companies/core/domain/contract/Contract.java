package com.nfb.modules.companies.core.domain.contract;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contrancts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Contract extends BaseEntity {
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Column(name = "company_id", nullable = false)
    private long companyID;

    @Column(name = "equipment_id", nullable = false)
    private long equipmentId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "pickup_date", nullable = false)
    private LocalDateTime pickupDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
}
