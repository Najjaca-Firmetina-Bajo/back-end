package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.QRCodeRepository;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public PenaltyService(AppointmentRepository appointmentRepository, QRCodeRepository qrCodeRepository, RegisteredUserRepository registeredUserRepository) {
        this.appointmentRepository = appointmentRepository;
        this.qrCodeRepository = qrCodeRepository;
        this.registeredUserRepository = registeredUserRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void checkAndApplyPenalties() {
        List<Appointment> expiredAppointments = appointmentRepository.findExpiredAppointments(LocalDateTime.now());
        for (Appointment appointment : expiredAppointments) {
            List<QRCode> qrCodes = qrCodeRepository.findByAppointmentId(appointment.getId());
            for (QRCode qrCode : qrCodes) {
                if (qrCode.getStatus().equals(QRStatus.NEW)) {
                    qrCode.setStatus(QRStatus.EXPIRED);
                    qrCodeRepository.updateStatusToExpired(qrCode.getId());
                    RegisteredUser user = qrCode.getRegisteredUser();
                    registeredUserRepository.updatePenalPoints(user.getPenalPoints() + 2, user.getId());
                }
            }
        }
    }
}
