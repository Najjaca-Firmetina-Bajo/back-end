package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.appointment.ExtraordinaryAppointment;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.repositories.ExtraordinaryAppointmentRepository;
import com.nfb.modules.companies.core.repositories.WorkingDayRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ExtraordinaryAppointmentService {

    private final ExtraordinaryAppointmentRepository extraordinaryAppointmentRepository;
    private final CompanyService companyService;
    private final WorkingDayRepository workingDayRepository;
    private final AppointmentService appointmentService;
    @Autowired
    public ExtraordinaryAppointmentService(ExtraordinaryAppointmentRepository extraordinaryAppointmentRepository, CompanyService companyService, WorkingDayRepository workingDayRepository, AppointmentService appointmentService){
        this.extraordinaryAppointmentRepository = extraordinaryAppointmentRepository;
        this.companyService = companyService;
        this.workingDayRepository = workingDayRepository;
        this.appointmentService = appointmentService;
    }

    public boolean checkIfAdministratorHasAppointment(long administratorId, Date date){
        if(extraordinaryAppointmentRepository.checkIfAdministratorHasAppointment(administratorId,date) == null) return false;
        return true;
    }


}
