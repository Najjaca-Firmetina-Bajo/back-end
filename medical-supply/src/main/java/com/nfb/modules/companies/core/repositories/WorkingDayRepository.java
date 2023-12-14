package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {
}
