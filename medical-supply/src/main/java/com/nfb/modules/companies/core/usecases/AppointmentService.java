package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import com.nfb.modules.stakeholders.core.usecases.EmailSender;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CompanyAdministratorRepository companyAdministratorRepository;
    private final QRCodeService qrCodeService;
    private final EmailSender emailSender;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, CompanyAdministratorRepository companyAdministratorRepository, QRCodeService qrCodeService, EmailSender emailSender) {
        this.appointmentRepository = appointmentRepository;
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.qrCodeService = qrCodeService;
        this.emailSender = emailSender;
    }


    public List<Appointment> getAll() { return appointmentRepository.findAll(); }

    public List<Appointment> findDownloadedAppointments() { return appointmentRepository.findDownloadedAppointments(); }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public long downloadEquipment(long appointmentId, long qrCodeId) {

        Appointment appointment = appointmentRepository.findById(appointmentId);
        CompanyAdministrator ca = appointment.getCompanyAdministrator();
        List<Appointment> appointmentsOfLoggedCA = appointmentRepository.findAppointmentsWithCompanyAdministrator(ca);

        boolean canDownload = true;
        for(Appointment a: appointmentsOfLoggedCA) {
            LocalDateTime tresholdTime = LocalDateTime.now().minusSeconds(20);
            if(a.getDownloadedAt() != null) {
                if(a.getDownloadedAt().isAfter(tresholdTime)) {
                    canDownload = false;
                    break;
                }
            }
        }

        if(canDownload) {
            appointmentRepository.updateIsDownloaded(true, appointmentId);
            appointmentRepository.updateDownloadedAt(LocalDateTime.now(), appointmentId);
            QRCode qr = qrCodeService.findById(qrCodeId);
            RegisteredUser ru = qr.getRegisteredUser();
            emailSender.sendReservationExecutionEmail(ru, qr);
            return appointmentId;
        }
        else {
            return -1L;
        }
    }

    public List<Appointment> findExpiredAppointments() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return appointmentRepository.findExpiredAppointments(currentDateTime);
    }

    public List<Appointment> findNonExpiredNotDownloadedAppointments() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return appointmentRepository.findNonExpiredNotDownloadedAppointments(currentDateTime);
    }

    public List<Appointment> getAllAvailable() {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> hasNoNewOrProcessedQRCodes(appointment))
                .collect(Collectors.toList());
    }

    private boolean hasNoNewOrProcessedQRCodes(Appointment appointment) {
        // Assuming QRCode has a getStatus() method that returns the status
        return appointment.getQRCodes().stream()
                .noneMatch(qrCode -> QRStatus.NEW.equals(qrCode.getStatus()) || QRStatus.PROCESSED.equals(qrCode.getStatus()));
    }
    public List<Appointment> getBy(long workingDayId) { return appointmentRepository.findByWorkingDayId(workingDayId); }

    public Appointment getById(long id) {
        return appointmentRepository.getById(id);
    }
}
