package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {
     List<WorkingDay> findByWorkingCalendarId(long workingCalendarId);

     @Query("SELECT wd\n" +
             "FROM WorkingDay wd\n" +
             "JOIN WorkingCalendar wc ON wd.workingCalendar.id = wc.id\n" +
             "JOIN Company c ON wc.company.id = c.id\n" +
             "WHERE DATE(wd.date) = :date AND c.id = :companyId")
     WorkingDay checkIfCompanyIsWorking(long companyId, Date date);
}
