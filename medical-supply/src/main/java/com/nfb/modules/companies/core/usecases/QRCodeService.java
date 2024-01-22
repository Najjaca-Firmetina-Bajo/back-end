
package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import com.nfb.modules.companies.core.repositories.QRCodeRepository;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final AppointmentRepository appointmentRepository;
    private final EquipmentRepository equipmentRepository;


    @Autowired
    public QRCodeService(QRCodeRepository qrCodeRepository,
                         RegisteredUserRepository registeredUserRepository,
                         AppointmentRepository appointmentRepository,
                         EquipmentRepository equipmentRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.appointmentRepository = appointmentRepository;
        this.equipmentRepository = equipmentRepository;
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

                // Retrieve equipment based on the provided equipment IDs
                List<Equipment> reservedEquipment = equipmentRepository.findByIdIn(qrCodeDto.getReservedEquipmentIds());
                qrCode.setReservedEquipment(reservedEquipment);

                return qrCodeRepository.save(qrCode);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
