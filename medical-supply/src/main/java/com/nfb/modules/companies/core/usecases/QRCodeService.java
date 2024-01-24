
package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.EquipmentQuantityDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
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
                Company company = appointment.getCompanyAdministrator().getCompany();
                if(company == null) return null;

                List<CompanyEquipment> companyEquipmentList = company.getCompanyEquipmentList();
                QRCode qrCode = new QRCode(qrCodeDto.getCode(), qrCodeDto.getStatus(), user, appointment);


                List<EquipmentQuantityDto> qrEquipmentList = qrCodeDto.getReservedEquipment();

                //dal su ispravni svi eqipID
                List<QREquipment> returnList = getQrEquipments(qrEquipmentList, qrCode);
                if (returnList == null) return null;

                //TODO: dal je ko rezervisao
                if(!appointment.getQRCodes().isEmpty() || appointment.getQRCodes() == null) return null;

                //TODO: dal je korisnik vec rezervisao ranije
                if (appointment.getCanceledQRCodes().stream().anyMatch(qr -> qr.getUser() != null && qr.getUser().getId().equals(user.getId()))) return null;  // User has already made a reservation

                //TODO: dal je kolicina opreme na lageru uopste
                if (!isEquipmentQuantityAvailable(companyEquipmentList, qrEquipmentList)) return null;

                qrCode.setReservedEquipment(returnList);

                return qrCodeRepository.save(qrCode);

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    private boolean isEquipmentQuantityAvailable(List<CompanyEquipment> companyEquipmentList, List<EquipmentQuantityDto> qrEquipmentList) {
        for (EquipmentQuantityDto qrEquipment : qrEquipmentList) {
            long equipmentId = qrEquipment.getEquipmentId();
            int requestedQuantity = qrEquipment.getQuantity();

            CompanyEquipment companyEquipment = companyEquipmentList.stream()
                    .filter(ce -> ce.getEquipment().getId() == equipmentId)
                    .findFirst()
                    .orElse(null);

            if (companyEquipment == null || companyEquipment.getQuantity() < requestedQuantity) {
                return false;
            }
        }

        return true;
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
