package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.repositories.WorkingCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingCalendarService {

    private final WorkingCalendarRepository workingCalendarRepository;

    @Autowired
    public WorkingCalendarService(WorkingCalendarRepository workingCalendarRepository) {
        this.workingCalendarRepository = workingCalendarRepository;
    }
}
