package com.nfb.medicalsupply;

import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.*;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.*;
import com.nfb.modules.companies.core.usecases.QRCodeService;
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
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.transaction.UnexpectedRollbackException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QRcodeServiceTests {


    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private CompanyEquipmentRepository companyEquipmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private QRCodeService qrCodeService;

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

        Equipment e1 = new Equipment();
        e1.setName("Equipment 1");
        e1.setType("T1");
        e1.setDescription("D1");
        e1.setPrice(10.0);
        equipmentRepository.save(e1);

        Equipment e2 = new Equipment();
        e2.setName("Equipment 2");
        e2.setType("T2");
        e2.setDescription("D2");
        e2.setPrice(11.0);
        equipmentRepository.save(e2);

        Equipment e3 = new Equipment();
        e3.setName("Equipment 3");
        e3.setType("T1");
        e3.setDescription("D3");
        e3.setPrice(13.0);
        equipmentRepository.save(e3);

        Company c = new Company();
        c.setName("Company 1");
        c.setAddress("U-1-G-D");
        c.setAverageRating(4.33);
        companyRepository.save(c);

        List<CompanyEquipment> lce = new ArrayList<>();

        CompanyEquipment ce1 = new CompanyEquipment();
        ce1.setCompany(c);
        ce1.setEquipment(e1);
        ce1.setQuantity(10);
        companyEquipmentRepository.save(ce1);
        lce.add(ce1);

        CompanyEquipment ce2 = new CompanyEquipment();
        ce2.setCompany(c);
        ce2.setEquipment(e2);
        ce2.setQuantity(20);
        companyEquipmentRepository.save(ce2);
        lce.add(ce2);

        CompanyEquipment ce3 = new CompanyEquipment();
        ce3.setCompany(c);
        ce3.setEquipment(e3);
        ce3.setQuantity(30);
        companyEquipmentRepository.save(ce3);
        lce.add(ce3);

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

        Appointment ap = new Appointment();
        ap.setPickUpDate(LocalDateTime.now());
        ap.setDuration(3);
        ap.setType(AppointmentType.Predefined);
        ap.setDownloaded(false);
        ap.setReservationNumber(222);
        ap.setQRCodes(new ArrayList<>());
        ap.setCompanyAdministrator(ca);
        ap.setWinnerId(-1);
        appointmentRepository.save(ap);

    }

@Test()
    public void testEquipmentReservation() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                QRCode qrc = new QRCode();

                QREquipment qre1 = new QREquipment(qrc, equipmentRepository.findById(1L).orElse(null), 10);
                QREquipment qre2 = new QREquipment(qrc, equipmentRepository.findById(2L).orElse(null), 20);
                QREquipment qre3 = new QREquipment(qrc, equipmentRepository.findById(3L).orElse(null), 30);

                List<QREquipment> lqre = new ArrayList<>();
                lqre.add(qre1);
                lqre.add(qre2);
                lqre.add(qre3);

                qrc.setUser(registeredUserRepository.findById(1L));
                qrc.setAppointment(appointmentRepository.findById(1L));
                qrc.setCode("ABC123");
                qrc.setStatus(QRStatus.NEW);
                qrc.setReservedEquipment(lqre);

                QRCodeDto qrd = new QRCodeDto(qrc);
                qrCodeService.addQRCodeFromDto(qrd);
                System.out.println("Zavrsio thread 1");
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                QRCode qrc = new QRCode();

                QREquipment qre1 = new QREquipment(qrc, equipmentRepository.findById(1L).orElse(null), 10);
                QREquipment qre2 = new QREquipment(qrc, equipmentRepository.findById(2L).orElse(null), 20);
                QREquipment qre3 = new QREquipment(qrc, equipmentRepository.findById(3L).orElse(null), 30);

                List<QREquipment> lqre = new ArrayList<>();
                lqre.add(qre1);
                lqre.add(qre2);
                lqre.add(qre3);

                qrc.setUser(registeredUserRepository.findById(2L));
                qrc.setAppointment(appointmentRepository.findById(1L));
                qrc.setCode("XYZ123");
                qrc.setStatus(QRStatus.NEW);
                qrc.setReservedEquipment(lqre);

                QRCodeDto qrd = new QRCodeDto(qrc);
                qrCodeService.addQRCodeFromDto(qrd);
                System.out.println("Zavrsio thread 2");
            }
        });
        try {
            future2.get();
            future1.get();

            // Retrieve all QR codes from the repository
            List<QRCode> allQRCodes = qrCodeService.getAll();
            System.out.println("QRCodes: " + allQRCodes.size());
            // Assert that there is exactly one QR code
            assertEquals(1, allQRCodes.size());
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


}
