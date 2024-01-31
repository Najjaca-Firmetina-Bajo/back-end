package com.nfb.medicalsupply;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.*;
import com.nfb.modules.companies.core.usecases.AppointmentService;
import com.nfb.modules.companies.core.usecases.CompanyEquipmentService;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTests {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;
    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Before
    public void setUp() throws Exception {

        RegisteredUser ru1 = new RegisteredUser();
        ru1.setUsername("2001sk2001@gmail.com");
        ru1.setPassword("12345678");
        ru1.setName("Stefan");
        ru1.setSurname("Kovacevic");
        ru1.setCity("Gacko");
        ru1.setCountry("BiH, RS");
        ru1.setOccupation("oc1");
        ru1.setCompanyInfo("ci1");
        ru1.setPhoneNumber("066123987");
        ru1.setPenalPoints(0);
        ru1.setEnabled(true);
        registeredUserRepository.save(ru1);

        RegisteredUser ru2 = new RegisteredUser();
        ru2.setUsername("2001im2001@gmail.com");
        ru2.setPassword("12345678");
        ru2.setName("Ivan");
        ru2.setSurname("Mikic");
        ru2.setCity("Novi Sad");
        ru2.setCountry("Srbija");
        ru2.setOccupation("oc2");
        ru2.setCompanyInfo("ci2");
        ru2.setPhoneNumber("066123987");
        ru2.setPenalPoints(1);
        ru1.setEnabled(true);
        registeredUserRepository.save(ru2);

        Company c = new Company();
        c.setName("Company 1");
        c.setAddress("U-1-G-D");
        c.setAverageRating(4.33);
        companyRepository.save(c);

        CompanyAdministrator ca = new CompanyAdministrator();
        ca.setCompany(c);
        ca.setUsername("2001dn2001@gmail.com");
        ca.setPassword("12345678");
        ca.setName("Drasko");
        ca.setSurname("Novakovic");
        ca.setCity("Secanj");
        ca.setCountry("Srbija");
        ca.setOccupation("oc3");
        ca.setCompanyInfo("ci3");
        ca.setPhoneNumber("066123987");
        companyAdministratorRepository.save(ca);

        Appointment ap1 = new Appointment();
        ap1.setPickUpDate(LocalDateTime.of(2024, 2, 2, 12, 0));
        ap1.setDuration(1);
        ap1.setType(AppointmentType.Predefined);
        ap1.setDownloaded(false);
        ap1.setReservationNumber(222);
        ap1.setQRCodes(new ArrayList<>());
        ap1.setCompanyAdministrator(ca);
        appointmentRepository.save(ap1);

        Appointment ap2 = new Appointment();
        ap2.setPickUpDate(LocalDateTime.of(2024, 2, 2, 14, 0));
        ap2.setDuration(2);
        ap2.setType(AppointmentType.Predefined);
        ap2.setDownloaded(false);
        ap2.setReservationNumber(222);
        ap2.setQRCodes(new ArrayList<>());
        ap2.setCompanyAdministrator(ca);
        appointmentRepository.save(ap2);

        QRCode qrCode1 = new QRCode();
        qrCode1.setUser(ru1);
        qrCode1.setAppointment(ap1);
        qrCode1.setCode("ABC123");
        qrCode1.setStatus(QRStatus.NEW);
        qrCode1.setReservedEquipment(new ArrayList<>());
        qrCodeRepository.save(qrCode1);

        QRCode qrCode2 = new QRCode();
        qrCode2.setUser(ru2);
        qrCode2.setAppointment(ap2);
        qrCode2.setCode("XYZ123");
        qrCode2.setStatus(QRStatus.NEW);
        qrCode2.setReservedEquipment(new ArrayList<>());
        qrCodeRepository.save(qrCode2);
    }


    @Test
    public void testCAisPresentAtTwoAppointmentsAtSameTime() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Long> future1 = executor.submit(() -> {
            System.out.println("Startovan Thread 1");
            var ret = appointmentService.downloadEquipment(1L, 1L);
            System.out.println("Zavrsen Thread 1");
            return ret;
        });

        Future<Long> future2 = executor.submit(() -> {
            System.out.println("Startovan Thread 2");
            var ret = appointmentService.downloadEquipment(2L, 2L);
            System.out.println("Zavrsen Thread 2");
            return ret;
        });

        // Čekamo završetak izvršavanja oba thread-a
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Proveravamo vraćene vrednosti
        try {
            Long result1 = future1.get();
            Long result2 = future2.get();

            if (result1 == -1L || result2 == -1L) {
                System.out.println(result1);
                System.out.println(result2);
                System.out.println("Test passed!");
            } else {
                System.out.println(result1);
                System.out.println(result2);
                System.out.println("Test failed!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
