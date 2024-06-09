package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.*;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.WorkingCalendarRepository;
import com.nfb.modules.companies.core.repositories.WorkingDayRepository;
import com.nfb.modules.stakeholders.API.dtos.AdminInfoDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CompanyAppointmentService {
    private final CompanyRepository companyRepository;
    private final AppointmentRepository appointmentRepository;
    private final CompanyAdministratorRepository companyAdministratorRepository;
    private final WorkingCalendarRepository workingCalendarRepository;
    private final WorkingDayRepository workingDayRepository;

    @Autowired
    public CompanyAppointmentService(CompanyRepository companyRepository, AppointmentRepository appointmentRepository,
                                     CompanyAdministratorRepository companyAdministratorRepository,
                                     WorkingCalendarRepository workingCalendarRepository,
                                     WorkingDayRepository workingDayRepository) {
        this.companyRepository = companyRepository;
        this.appointmentRepository = appointmentRepository;
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.workingCalendarRepository = workingCalendarRepository;
        this.workingDayRepository = workingDayRepository;
    }

    @Transactional
    public CompanyInfoDto getById(Long adminId) {
        Company company = companyRepository.findByAdminId(adminId);
        List<Appointment> appointments = appointmentRepository.findAllByCompanyAdministratorId(adminId);
        List<CompanyAdministrator> admins = company.getAdministrators();

        CompanyInfoDto companyInfoDto = new CompanyInfoDto();
        companyInfoDto.setId(company.getId());
        companyInfoDto.setName(company.getName());
        companyInfoDto.setAddress(company.getAddress());
        companyInfoDto.setDescription(company.getDescription());
        companyInfoDto.setAverageRating(company.getAverageRating());

        List<AppointmentInfoDto> appointmentDtos = appointments.stream().map(appointment -> {
            AppointmentInfoDto dto = new AppointmentInfoDto();
            dto.setId(appointment.getId());
            dto.setPickUpDate(appointment.getPickUpDate());
            dto.setDuration(appointment.getDuration());
            dto.setType(appointment.getType());
            dto.setDownloaded(appointment.isDownloaded());
            dto.setReservationNumber(appointment.getReservationNumber());
            dto.setWorkingDayId(appointment.getWorkingDay() != null ? appointment.getWorkingDay().getId() : null);
            dto.setQrCodeId(appointment.getQRCodes() != null && !appointment.getQRCodes().isEmpty() ? appointment.getQRCodes().get(0).getId() : null); // assuming the first QR code is the desired one
            return dto;
        }).collect(Collectors.toList());
        companyInfoDto.setAvailableAppointments(appointmentDtos);


        List<AdminInfoDto> adminDtos = admins.stream().map(admin -> {
            AdminInfoDto dto = new AdminInfoDto();
            dto.setId(admin.getId());
            dto.setEmail(admin.getUsername());
            dto.setName(admin.getName());
            dto.setSurname(admin.getSurname());
            dto.setCity(admin.getCity());
            dto.setCountry(admin.getCountry());
            dto.setPhoneNumber(admin.getPhoneNumber());
            return dto;
        }).collect(Collectors.toList());
        companyInfoDto.setAdmins(adminDtos);

        return companyInfoDto;
    }

    public WorkingCalendarInfoDto getWorkingCalendar(long companyId) {
        WorkingCalendar workingCalendar = workingCalendarRepository.findByCompanyId(companyId);

        if (workingCalendar == null) {
            return null;
        }

        WorkingCalendarInfoDto workingCalendarDto = new WorkingCalendarInfoDto();
        workingCalendarDto.setId(workingCalendar.getId());

        List<WorkingDayInfoDto> workingDayDtos = new ArrayList<>();
        for (WorkingDay workingDay : workingCalendar.getWorkingDays()) {
            WorkingDayInfoDto workingDayDto = new WorkingDayInfoDto();
            workingDayDto.setId(workingDay.getId());
            workingDayDto.setDate(workingDay.getDate());
            workingDayDto.setEndDate(workingDay.getEndDate());
            workingDayDtos.add(workingDayDto);
        }
        workingCalendarDto.setWorkingDays(workingDayDtos);

        return workingCalendarDto;
    }

    @Transactional
    public void create(CreateAppointmentDto appointmentDto) {
        CompanyAdministrator companyAdministrator = companyAdministratorRepository.findOneById(appointmentDto.getAdminId());
        WorkingDay workingDay = workingDayRepository.findWorkingDayById(appointmentDto.getWorkingDayId())
                .orElseThrow(() -> new NoSuchElementException("No WorkingDay found for the provided date"));

        Appointment appointment = new Appointment(appointmentDto.getPickUpDate(), appointmentDto.getDuration(), AppointmentType.Extraordinary, false,-1, workingDay, -1, companyAdministrator);

        appointmentRepository.save(appointment);
    }
}
