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
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import com.nfb.modules.stakeholders.core.usecases.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QRcodeServiceTests {

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


    @Test
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

        //QRCode qrc = new QRCode();
        /*
        QREquipment qre1 = new QREquipment(qrc, e1, 10);
        QREquipment qre2 = new QREquipment(qrc, e2, 20);
        QREquipment qre3 = new QREquipment(qrc, e3, 30);

        List<QREquipment> lqre = new ArrayList<>();
        lqre.add(qre1);
        lqre.add(qre2);
        lqre.add(qre3);

        qrc.setUser(ru);
        qrc.setAppointment(ap);
        qrc.setCode("ABC123");
        qrc.setStatus(QRStatus.NEW);
        qrc.setReservedEquipment(lqre);

        QRCodeDto qrd = new QRCodeDto(qrc);

        QRCode qrCode = new QRCode(qrd.getCode(), qrd.getStatus(), ru, ap);

        when(registeredUserRepositoryMock.findById(qrd.getRegisteredUserId())).thenReturn(Optional.of(ru));
        when(appointmentRepositoryMock.findById(qrd.getAppointmentId())).thenReturn(Optional.of(ap));
        when(equipmentRepositoryMock.findById(eq1.getEquipmentId())).thenReturn(Optional.of(e1));
        when(equipmentRepositoryMock.findById(eq2.getEquipmentId())).thenReturn(Optional.of(e2));
        when(equipmentRepositoryMock.findById(eq3.getEquipmentId())).thenReturn(Optional.of(e3));
        when(qrCodeRepositoryMock.save(qrCode)).thenReturn(qrCode);
        doNothing().when(emailSenderMock).sendQREmail(ru, qrCode);

        QRCode retVal = qrCodeServiceMock.addQRCodeFromDto(qrd);
        */

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
            //latch.countDown();
        });

        thread1.start();

        try {
            Thread.sleep(150); // Kratka pauza od 100 milisekundi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Startujemo drugu nit
        thread2.start();

        // Čekamo da se obe niti završe
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Provera rezultata
        assertThat(retVal[0].getCode()).isEqualTo("ABC123"); // Proveravamo rezultat prve niti
        assertThat(retVal[1]).isNull();
        //assertThat(retVal[1].getCode()).isEqualTo("XYZ123");

    }

    /*
    @Test
    public void addQRCodeFromDtoTest2() {
        Role r = new Role((long)0, "REGISTERED_USER");

        RegisteredUser ru = new RegisteredUser("im1.com", "12345678", r, "Ivan",
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

        QRCode qrc = new QRCode();

        QREquipment qre1 = new QREquipment(qrc, e1, 10);
        QREquipment qre2 = new QREquipment(qrc, e2, 20);
        QREquipment qre3 = new QREquipment(qrc, e3, 30);

        List<QREquipment> lqre = new ArrayList<>();
        lqre.add(qre1);
        lqre.add(qre2);
        lqre.add(qre3);

        qrc.setUser(ru);
        qrc.setAppointment(ap);
        qrc.setCode("XYZ123");
        qrc.setStatus(QRStatus.NEW);
        qrc.setReservedEquipment(lqre);

        QRCodeDto qrd = new QRCodeDto(qrc);

        QRCode qrCode = new QRCode(qrd.getCode(), qrd.getStatus(), ru, ap);

        when(registeredUserRepositoryMock.findById(qrd.getRegisteredUserId())).thenReturn(Optional.of(ru));
        when(appointmentRepositoryMock.findById(qrd.getAppointmentId())).thenReturn(Optional.of(ap));
        when(equipmentRepositoryMock.findById(eq1.getEquipmentId())).thenReturn(Optional.of(e1));
        when(equipmentRepositoryMock.findById(eq2.getEquipmentId())).thenReturn(Optional.of(e2));
        when(equipmentRepositoryMock.findById(eq3.getEquipmentId())).thenReturn(Optional.of(e3));
        when(qrCodeRepositoryMock.save(qrCode)).thenReturn(qrCode);
        doNothing().when(emailSenderMock).sendQREmail(ru, qrCode);

        QRCode retVal = qrCodeServiceMock.addQRCodeFromDto(qrd);

        System.out.println(retVal.getCode());
        //assertThat(retVal.getCode().equals("ABC123"));
        assertThat(retVal.getCode()).isEqualTo("ABC123");
        //assertThat(retVal.getUser()).isNotEqualTo(ru);

    }
    */
}
