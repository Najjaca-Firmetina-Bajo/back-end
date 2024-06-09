package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.CreateAppointmentDto;
import com.nfb.modules.companies.core.domain.appointment.*;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.WorkingDayRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import com.nfb.modules.stakeholders.core.usecases.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CompanyAdministratorRepository companyAdministratorRepository;
    private final QRCodeService qrCodeService;
    private final EmailSender emailSender;
    private final CompanyService companyService;
    private final WorkingDayRepository workingDayRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, CompanyAdministratorRepository companyAdministratorRepository, QRCodeService qrCodeService, EmailSender emailSender, CompanyService companyService, WorkingDayRepository workingDayRepository) {
        this.appointmentRepository = appointmentRepository;
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.qrCodeService = qrCodeService;
        this.emailSender = emailSender;
        this.companyService = companyService;
        this.workingDayRepository = workingDayRepository;
    }


    public List<Appointment> getAll() { return appointmentRepository.findAll(); }

    public List<Appointment> findDownloadedAppointments() { return appointmentRepository.findDownloadedAppointments(); }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public long downloadEquipment(long appointmentId, long qrCodeId) {

        Appointment appointment = appointmentRepository.findById(appointmentId);
        CompanyAdministrator ca = companyAdministratorRepository.findOneById(appointment.getCompanyAdministrator().getId());

        boolean canDownload = true;
        for(Appointment a: ca.getAppointments()) {
            LocalDateTime tresholdTime = LocalDateTime.now().minusSeconds(60);
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
        return appointmentRepository.findById(id);
    }

    public boolean checkIfAdministratorHasAppointment(long administratorId, Date date){
        if(appointmentRepository.checkIfAdministratorHasAppointment(administratorId,date) == null) return false;
        return true;
    }

    public List<Appointment> getUsersDownloadedAppointments(long winnerId){
        return appointmentRepository.getUsersDownloadedAppointments(winnerId);
    }

    public List<Appointment> sortAppointments(String ascOrDesc, String type, long winnerId){
        if(type.equals("date")){
            if(ascOrDesc.equals("asc")) return appointmentRepository.sortAppointmentsByDateAsc(winnerId);
            return appointmentRepository.sortAppointmentsByDateDesc(winnerId);
        }
        else if(type.equals("duration")){
            if(ascOrDesc.equals("asc")) return appointmentRepository.sortAppointmentsByDurationAsc(winnerId);
            return appointmentRepository.sortAppointmentsByDurationDesc(winnerId);
        }
        return null;
    }

    public List<Appointment> createIfCompanyIsWorking(Date date, long companyId) {
        try {

            if (!companyService.checkIfCompanyIsWorking(companyId, date)) {
                return null;
            }

            WorkingDay companiesWorkingDay = workingDayRepository.checkIfCompanyIsWorking(companyId, date);

            List<CompanyAdministrator> administrators = companyService.getCompanyAdministrators(companyId);
            List<Long> administratorsIds = new ArrayList<>();

            for (CompanyAdministrator administrator : administrators) {
                administratorsIds.add(administrator.getId());
            }

            List<Appointment> extraordinaryAppointments = appointmentRepository.getCompaniesNotDowloadedAppointments(administratorsIds, date);

            //if (extraordinaryAppointments.isEmpty()) {
                LocalDateTime currentDateTime = LocalDateTime.ofInstant(companiesWorkingDay.getDate().toInstant(), ZoneId.systemDefault());
                LocalDateTime endDateTime = LocalDateTime.ofInstant(companiesWorkingDay.getEndDate().toInstant(), ZoneId.systemDefault());

                while (!currentDateTime.isAfter(endDateTime)) {
                    Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());

                    for (long administratorId : administratorsIds) {
                        Random random = new Random();
                        try {
                            if (!checkIfAdministratorHasAppointment(administratorId, currentDate)) {
                                CompanyAdministrator administrator = companyAdministratorRepository.getById(administratorId);
                                Appointment newExtraordinaryAppointment = new Appointment(currentDateTime, 30, AppointmentType.Extraordinary, false, random.nextInt(1000) + 1, companiesWorkingDay, -1, administrator);
                                extraordinaryAppointments.add(newExtraordinaryAppointment);
                            }
                        } catch (Exception e) {
                            // Handle the exception (e.g., log it) if checkIfAdministratorHasAppointment fails
                            e.printStackTrace();
                        }
                    }
                    currentDateTime = currentDateTime.plusHours(1);
                }
            //}

            for (Appointment a : extraordinaryAppointments) {
                try {
                    appointmentRepository.save(a);
                } catch (Exception e) {
                    // Handle the exception (e.g., log it) if saving the appointment fails
                    e.printStackTrace();
                }
            }

            return extraordinaryAppointments;
        } catch (Exception e) {
            // Handle any unexpected exception that might occur during the execution
            e.printStackTrace();
            return null;
        }
    }

    public void save(Appointment appointment) {
        appointmentRepository.saveAndFlush(appointment);
    }

}
