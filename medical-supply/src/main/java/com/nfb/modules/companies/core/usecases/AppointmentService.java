package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final EquipmentRepository equipmentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,RegisteredUserRepository registeredUserRepository,EquipmentRepository equipmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.equipmentRepository = equipmentRepository;
    }


    public List<Appointment> getAll() { return appointmentRepository.findAll(); }
    public List<Appointment> getBy(long workingDayId) { return appointmentRepository.findByWorkingDayId(workingDayId); }

    public Appointment updateAppointment(AppointmentDto updatedAppointment) {
        var appointment = appointmentRepository.findById(updatedAppointment.getId());
        var user = registeredUserRepository.findById(updatedAppointment.getId());
        var eqipment = equipmentRepository.findByIdIn(updatedAppointment.getReservedEquipmentIds());
        if(appointment != null && user != null && eqipment.size() == updatedAppointment.getReservedEquipmentIds().size()){
            //appointment.setRegisteredUser(user);
            //appointment.setReservedEquipment(eqipment);

        }

        return appointmentRepository.save(appointment);

    }
}
