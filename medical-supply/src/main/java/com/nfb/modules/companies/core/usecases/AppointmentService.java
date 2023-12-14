package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAll() { return appointmentRepository.findAll(); }
    public List<Appointment> getBy(long workingDayId) { return appointmentRepository.findByWorkingDayId(workingDayId); }
}
