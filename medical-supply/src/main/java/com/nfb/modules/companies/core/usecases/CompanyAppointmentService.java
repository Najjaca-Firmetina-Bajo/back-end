package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.AppointmentInfoDto;
import com.nfb.modules.companies.API.dtos.CompanyInfoDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.stakeholders.API.dtos.AdminInfoDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyAppointmentService {
    private final CompanyRepository companyRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CompanyAppointmentService(CompanyRepository companyRepository, AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
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
}
