package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import com.nfb.modules.companies.core.repositories.CompanyEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyEquipmentService {

    private final CompanyEquipmentRepository companyEquipmentRepository;
    @Autowired
    public CompanyEquipmentService(CompanyEquipmentRepository companyEquipmentRepository) {
        this.companyEquipmentRepository = companyEquipmentRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int pickUpEquipment(long equipmentId, long companyId, int quantity) {
        CompanyEquipment ce = companyEquipmentRepository.findByCompanyIdAndEquipmentId(companyId, equipmentId);
        int newQuantity = ce.getQuantity() - quantity;
        companyEquipmentRepository.updateQuantity(equipmentId, companyId, newQuantity);
        return newQuantity;
    }
}
