package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.companies.core.repositories.WorkingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingDayService {
    private final WorkingDayRepository workingDayRepository;

    @Autowired
    public WorkingDayService(WorkingDayRepository workingDayRepository) {
        this.workingDayRepository = workingDayRepository;
    }

    public List<WorkingDay> getAll() { return workingDayRepository.findAll(); }
    public List<WorkingDay> getBy(long workingCalendarId) { return workingDayRepository.findByWorkingCalendarId(workingCalendarId); }
}
