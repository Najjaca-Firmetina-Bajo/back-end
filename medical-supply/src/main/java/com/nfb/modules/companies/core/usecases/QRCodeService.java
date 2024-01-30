
package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.EquipmentQuantityDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.*;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import com.nfb.modules.stakeholders.core.usecases.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final QREqipmentRepository qrEqipmentRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final AppointmentRepository appointmentRepository;
    private final EquipmentRepository equipmentRepository;
    private final CompanyRepository companyRepository;
    private final EmailSender emailSender;


    @Autowired
    public QRCodeService(QRCodeRepository qrCodeRepository,
                         RegisteredUserRepository registeredUserRepository,
                         AppointmentRepository appointmentRepository,
                         EquipmentRepository equipmentRepository,
                         QREqipmentRepository qrEqipmentRepository,
                         CompanyRepository companyRepository,
                         EmailSender emailSender) {
        this.qrCodeRepository = qrCodeRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.appointmentRepository = appointmentRepository;
        this.equipmentRepository = equipmentRepository;
        this.qrEqipmentRepository = qrEqipmentRepository;
        this.companyRepository = companyRepository;
        this.emailSender = emailSender;
    }

    public List<QRCode> getAll() {
        return qrCodeRepository.findAll();
    }

    public QRCode findById(long id) { return qrCodeRepository.findById(id).orElse(null); }

    public List<QRCode> getByAppointmentId(long appointmentId) {
        return qrCodeRepository.findByAppointmentId(appointmentId);
    }

    public List<QRCode> getByUserId(long userId) {
        return qrCodeRepository.findByRegisteredUser_Id(userId);
    }

    public QRCode addQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }


    @Transactional(readOnly = false , propagation = Propagation.REQUIRES_NEW)
    public QRCode addQRCodeFromDto(QRCodeDto qrCodeDto,Appointment appointment) {
        try {
            RegisteredUser user = registeredUserRepository.findById(qrCodeDto.getRegisteredUserId()).orElse(null);



            if (user != null && appointment != null) {
                Company company = appointment.getCompanyAdministrator().getCompany();
                if(company == null) {
                    System.out.println("Company is null");
                    return null;
                }


                //TODO: dal korisnik ima vise od 3 penala
                if(user.getPenalPoints() >= 3) {
                    System.out.println("User has 3+ penal points");
                    return null;
                }

                List<CompanyEquipment> companyEquipmentList = company.getCompanyEquipmentList();
                QRCode qrCode = new QRCode(qrCodeDto.getCode(), qrCodeDto.getStatus(), user, appointment);


                List<EquipmentQuantityDto> qrEquipmentList = qrCodeDto.getReservedEquipment();

                //dal su ispravni svi eqipID
                List<QREquipment> returnList = getQrEquipments(qrEquipmentList, qrCode);
                if (returnList == null) {
                    System.out.println("OREquipment list is null");
                    return null;
                }

                //TODO: dal je ko rezervisao
                if(!appointment.getQRCodes().isEmpty() || appointment.getQRCodes() == null) {
                    System.out.println("1");
                    return null;
                }

                //TODO: dal je korisnik vec rezervisao ranije
                if (appointment.getCanceledQRCodes().stream().anyMatch(qr -> qr.getUser() != null && qr.getUser().getId().equals(user.getId()))) {
                    System.out.println("2");
                    return null;
                }  // User has already made a reservation

                //TODO: dal je kolicina opreme na lageru uopste
                if (!isEquipmentQuantityAvailable(companyEquipmentList, qrEquipmentList)) {
                    System.out.println("3");
                    return null;
                }

                qrCode.setReservedEquipment(returnList);
                qrCode = qrCodeRepository.save(qrCode);
                appointment.setWinnerId(qrCode.getId());
                appointmentRepository.save(appointment);

                //emailSender.sendQREmail(user,qrCode);
                return qrCode;

            } else {
                System.out.println("!(user != null && appointment != null)");
                return null;
            }
        } catch (Exception e) {
            System.out.println("e");
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
                System.out.println(companyEquipment == null);
                System.out.println(companyEquipment.getQuantity() < requestedQuantity);
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

    public QRCode cancelQRCodeById(long id, RegisteredUser registeredUser) {
        Optional<QRCode> optionalQRCode = qrCodeRepository.findById(id);



        if (optionalQRCode.isPresent()) {
            QRCode qrCode = optionalQRCode.get();
            if(!Objects.equals(qrCode.getUser().getId(), registeredUser.getId())) return null;

            if(qrCode.getAppointment().getPickUpDate().isBefore(LocalDateTime.now())) return null;



            if (qrCode.getStatus() != QRStatus.CANCELED) {
                qrCode.setStatus(QRStatus.CANCELED);

                Duration timeDifference = Duration.between(LocalDateTime.now(), qrCode.getAppointment().getPickUpDate());

                // Check if the cancellation is within 24 hours of the appointment
                if (timeDifference.toHours() < 24) {
                    registeredUser.setPenalPoints(registeredUser.getPenalPoints() + 2); // Apply 2 penalty points
                } else {
                    registeredUser.setPenalPoints(registeredUser.getPenalPoints() + 1); // Apply 1 penalty point
                }

                return qrCodeRepository.save(qrCode);
            }
        }

        return null;
    }
}
