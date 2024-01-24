
package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.EquipmentQuantityDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.*;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final QREqipmentRepository qrEqipmentRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final AppointmentRepository appointmentRepository;
    private final EquipmentRepository equipmentRepository;
    private final CompanyRepository companyRepository;


    @Autowired
    public QRCodeService(QRCodeRepository qrCodeRepository,
                         RegisteredUserRepository registeredUserRepository,
                         AppointmentRepository appointmentRepository,
                         EquipmentRepository equipmentRepository,
                         QREqipmentRepository qrEqipmentRepository,
                         CompanyRepository companyRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.appointmentRepository = appointmentRepository;
        this.equipmentRepository = equipmentRepository;
        this.qrEqipmentRepository = qrEqipmentRepository;
        this.companyRepository = companyRepository;
    }

    public List<QRCode> getAll() {
        return qrCodeRepository.findAll();
    }

    public List<QRCode> getByAppointmentId(long appointmentId) {
        return qrCodeRepository.findByAppointmentId(appointmentId);
    }

    public QRCode addQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public QRCode addQRCodeFromDto(QRCodeDto qrCodeDto) {
        try {
            RegisteredUser user = registeredUserRepository.findById(qrCodeDto.getRegisteredUserId()).orElse(null);
            Appointment appointment = appointmentRepository.findById(qrCodeDto.getAppointmentId()).orElse(null);

            if (user != null && appointment != null) {

                QRCode qrCode = new QRCode(qrCodeDto.getCode(), qrCodeDto.getStatus(), user, appointment);


                List<EquipmentQuantityDto> qrEquipmentList = qrCodeDto.getReservedEquipment();

                List<QREquipment> returnList = getQrEquipments(qrEquipmentList, qrCode);
                if (returnList == null) return null;

                //TODO: add check for availability in company
                qrCode.setReservedEquipment(returnList);

                return qrCodeRepository.save(qrCode);

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    private List<QREquipment> getQrEquipments(List<EquipmentQuantityDto> qrEquipmentList, QRCode qrCode) {
        List<QREquipment> returnList = new ArrayList<>();
        for (var qe : qrEquipmentList) {
            Optional<Equipment> eq = equipmentRepository.findById(qe.getEquipmentId());
            if (eq.isPresent()) {
                Equipment equipment = eq.get();
                returnList.add(new QREquipment(qrCode,equipment, qe.getQuantity()));

            } else {
                return null;
            }
        }
        return returnList;
    }
}
