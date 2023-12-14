package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingCalendarRepository extends JpaRepository<WorkingCalendar, Long> {
    WorkingCalendar findByCompanyId(long companyId);
}
