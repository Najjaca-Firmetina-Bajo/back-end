package com.nfb.medicalsupply;

import com.nfb.modules.companies.API.dtos.EquipmentQuantityDto;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.UnexpectedRollbackException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QRcodeServiceTests {

    /*
    @Mock
    private QRCodeRepository qrCodeRepositoryMock;
    @Mock
    private RegisteredUserRepository registeredUserRepositoryMock;
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @Mock
    private EquipmentRepository equipmentRepositoryMock;
    @Mock
    private EmailSender emailSenderMock;

    @InjectMocks
    private QRCodeService qrCodeServiceMock;
    */

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

        //c.setCompanyEquipmentList(lce);
        //companyRepository.save(c);

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
        appointmentRepository.save(ap);

        //EquipmentQuantityDto eq1 = new EquipmentQuantityDto(e1.getId(), 10);
        //EquipmentQuantityDto eq2 = new EquipmentQuantityDto(e2.getId(), 20);
        //EquipmentQuantityDto eq3 = new EquipmentQuantityDto(e3.getId(), 30);
    }

    //@Test(expected = UnexpectedRollbackException.class)
    @Test(expected = UnexpectedRollbackException.class)
    public void testOptimisticLockingScenario() throws Throwable {

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
                Appointment app = appointmentRepository.findById(1L);
                qrc.setAppointment(app);
                qrc.setCode("ABC123");
                qrc.setStatus(QRStatus.NEW);
                qrc.setReservedEquipment(lqre);

                QRCodeDto qrd = new QRCodeDto(qrc);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                qrCodeService.addQRCodeFromDto(qrd,app);// bacice ObjectOptimisticLockingFailureException
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
                Appointment app = appointmentRepository.findById(1L);
                qrc.setAppointment(app);
                qrc.setCode("XYZ123");
                qrc.setStatus(QRStatus.NEW);
                qrc.setReservedEquipment(lqre);

                QRCodeDto qrd = new QRCodeDto(qrc);
                qrCodeService.addQRCodeFromDto(qrd,app);
                System.out.println("Zavrsio thread 2");
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }


    /*@Test
    public void addQRCodeFromDtoTest1() {
        Role r = new Role((long)0, "REGISTERED_USER");

        RegisteredUser ru1 = new RegisteredUser("2001sk2001@gmail.com", "12345678", r, "Stefan",
                                             "Kovacevic","Gacko", "BiH, RS", "066789654",
                                            "oc1", "ci1", 0);
        RegisteredUser ru2 = new RegisteredUser("2001im2001@gmail.com", "12345678", r, "Ivan",
                "Mikic","Novi Sad", "Srbija", "066789654",
                "oc2", "ci2", 0);

        Equipment e1 = new Equipment();
        e1.setName("Equipment 1");
        e1.setId(1);

        Equipment e2 = new Equipment();
        e2.setName("Equipment 2");
        e2.setId(2);

        Equipment e3 = new Equipment();
        e3.setName("Equipment 3");
        e3.setId(3);

        Company c = new Company();

        List<CompanyEquipment> lce = new ArrayList<>();

        CompanyEquipment ce1 = new CompanyEquipment();
        ce1.setCompany(c);
        ce1.setEquipment(e1);
        ce1.setQuantity(10);
        lce.add(ce1);

        CompanyEquipment ce2 = new CompanyEquipment();
        ce2.setCompany(c);
        ce2.setEquipment(e2);
        ce2.setQuantity(20);
        lce.add(ce2);

        CompanyEquipment ce3 = new CompanyEquipment();
        ce3.setCompany(c);
        ce3.setEquipment(e3);
        ce3.setQuantity(30);
        lce.add(ce3);

        c.setCompanyEquipmentList(lce);

        CompanyAdministrator ca = new CompanyAdministrator();
        ca.setCompany(c);

        Appointment ap = new Appointment();
        ap.setPickUpDate(LocalDateTime.now());
        ap.setDuration(3);
        ap.setType(AppointmentType.Predefined);
        ap.setDownloaded(false);
        ap.setReservationNumber(222);
        ap.setQRCodes(new ArrayList<>());
        ap.setCompanyAdministrator(ca);

        EquipmentQuantityDto eq1 = new EquipmentQuantityDto(e1.getId(), 10);
        EquipmentQuantityDto eq2 = new EquipmentQuantityDto(e2.getId(), 20);
        EquipmentQuantityDto eq3 = new EquipmentQuantityDto(e3.getId(), 30);

        QRCode[] retVal = new QRCode[2];

        Thread thread1 = new Thread(() -> {
            QRCode qrc = new QRCode();

            QREquipment qre1 = new QREquipment(qrc, e1, 10);
            QREquipment qre2 = new QREquipment(qrc, e2, 20);
            QREquipment qre3 = new QREquipment(qrc, e3, 30);

            List<QREquipment> lqre = new ArrayList<>();
            lqre.add(qre1);
            lqre.add(qre2);
            lqre.add(qre3);

            qrc.setUser(ru1);
            qrc.setAppointment(ap);
            qrc.setCode("ABC123");
            qrc.setStatus(QRStatus.NEW);
            qrc.setReservedEquipment(lqre);

            QRCodeDto qrd = new QRCodeDto(qrc);

            QRCode qrCode = new QRCode(qrd.getCode(), qrd.getStatus(), ru1, ap);

            when(registeredUserRepositoryMock.findById(qrd.getRegisteredUserId())).thenReturn(Optional.of(ru1));
            when(appointmentRepositoryMock.findById(qrd.getAppointmentId())).thenReturn(Optional.of(ap));
            when(equipmentRepositoryMock.findById(eq1.getEquipmentId())).thenReturn(Optional.of(e1));
            when(equipmentRepositoryMock.findById(eq2.getEquipmentId())).thenReturn(Optional.of(e2));
            when(equipmentRepositoryMock.findById(eq3.getEquipmentId())).thenReturn(Optional.of(e3));
            when(qrCodeRepositoryMock.save(qrCode)).thenReturn(qrCode);
            doNothing().when(emailSenderMock).sendQREmail(ru1, qrCode);

            retVal[0] = qrCodeServiceMock.addQRCodeFromDto(qrd);
            //latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            QRCode qrc = new QRCode();

            QREquipment qre1 = new QREquipment(qrc, e1, 10);
            QREquipment qre2 = new QREquipment(qrc, e2, 20);
            QREquipment qre3 = new QREquipment(qrc, e3, 30);

            List<QREquipment> lqre = new ArrayList<>();
            lqre.add(qre1);
            lqre.add(qre2);
            lqre.add(qre3);

            qrc.setUser(ru2);
            qrc.setAppointment(ap);
            qrc.setCode("XYZ123");
            qrc.setStatus(QRStatus.NEW);
            qrc.setReservedEquipment(lqre);

            QRCodeDto qrd = new QRCodeDto(qrc);

            QRCode qrCode = new QRCode(qrd.getCode(), qrd.getStatus(), ru2, ap);

            when(registeredUserRepositoryMock.findById(qrd.getRegisteredUserId())).thenReturn(Optional.of(ru2));
            when(appointmentRepositoryMock.findById(qrd.getAppointmentId())).thenReturn(Optional.of(ap));
            when(equipmentRepositoryMock.findById(eq1.getEquipmentId())).thenReturn(Optional.of(e1));
            when(equipmentRepositoryMock.findById(eq2.getEquipmentId())).thenReturn(Optional.of(e2));
            when(equipmentRepositoryMock.findById(eq3.getEquipmentId())).thenReturn(Optional.of(e3));
            when(qrCodeRepositoryMock.save(qrCode)).thenReturn(qrCode);
            doNothing().when(emailSenderMock).sendQREmail(ru2, qrCode);

            retVal[1] = qrCodeServiceMock.addQRCodeFromDto(qrd);
        });

        thread1.start();

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(retVal[0].getCode()).isEqualTo("ABC123"); // Proveravamo rezultat prve niti
        assertThat(retVal[1]).isNull();
        //assertThat(retVal[1].getCode()).isEqualTo("XYZ123");

    }*/

}
