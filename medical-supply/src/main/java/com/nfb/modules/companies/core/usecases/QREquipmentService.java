package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.repositories.QREqipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QREquipmentService {

    private QREqipmentRepository qrEqipmentRepository;

    @Autowired
    public QREquipmentService(QREqipmentRepository qrEqipmentRepository) {
        this.qrEqipmentRepository = qrEqipmentRepository;
    }

    public List<QREquipment> getAll() { return qrEqipmentRepository.findAll(); }
}
