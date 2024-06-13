package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.repositories.AppointmentRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

@Service
public class AnalyticsService {
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public AnalyticsService(CompanyAdministratorRepository companyAdministratorRepository, AppointmentRepository appointmentRepository) {
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Map<Integer, Long> getAppointmentCountByYear(Long companyId) {
        List<Long> adminIds = getAdminIdsByCompanyId(companyId);

        List<Object[]> results = appointmentRepository.countAppointmentsByYear(adminIds);
        return results.stream().collect(Collectors.toMap(
                result -> (Integer) result[0],
                result -> (Long) result[1]
        ));
    }

    public Map<Integer, Long> getAppointmentCountByQuarter(Long companyId, int year) {
        List<Long> adminIds = getAdminIdsByCompanyId(companyId);
        List<Object[]> results = appointmentRepository.countAppointmentsByQuarter(adminIds, year);
        Map<Integer, Long> appointmentCountByQuarter = aggregateAppointmentCounts(results);
        return sortAndFillMissingQuarters(appointmentCountByQuarter);
    }

    private List<Long> getAdminIdsByCompanyId(Long companyId) {
        return companyAdministratorRepository.findAllByCompanyId(companyId)
                .stream()
                .map(CompanyAdministrator::getId)
                .collect(Collectors.toList());
    }

    private Map<Integer, Long> aggregateAppointmentCounts(List<Object[]> results) {
        Map<Integer, Long> appointmentCountByQuarter = new HashMap<>();
        results.forEach(result -> {
            Integer quarter = ((BigDecimal) result[0]).intValue();
            Long count = (Long) result[1];
            appointmentCountByQuarter.put(quarter, count);
        });
        return appointmentCountByQuarter;
    }

    private Map<Integer, Long> sortAndFillMissingQuarters(Map<Integer, Long> appointmentCountByQuarter) {
        Map<Integer, Long> sortedAppointmentCountByQuarter = new TreeMap<>(appointmentCountByQuarter);
        for (int i = 1; i <= 4; i++) {
            sortedAppointmentCountByQuarter.putIfAbsent(i, 0L);
        }
        return sortedAppointmentCountByQuarter;
    }


    public Map<Integer, Long> getAppointmentCountByMonth(Long companyId, int year) {
        List<CompanyAdministrator> companyAdministrators = companyAdministratorRepository.findAllByCompanyId(companyId);
        List<Long> adminIds = companyAdministrators.stream()
                .map(CompanyAdministrator::getId)
                .collect(Collectors.toList());

        List<Object[]> results = appointmentRepository.countAppointmentsByMonth(adminIds, year);

        Map<Integer, Long> appointmentCountsByMonth = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            appointmentCountsByMonth.put(month, 0L);
        }

        results.forEach(result -> {
            int month = (int) result[0];
            long count = (long) result[1];
            appointmentCountsByMonth.put(month, count);
        });

        return sortMapByMonth(appointmentCountsByMonth);
    }

    private Map<Integer, Long> sortMapByMonth(Map<Integer, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }



}
