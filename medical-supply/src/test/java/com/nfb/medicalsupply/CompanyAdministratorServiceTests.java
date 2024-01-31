package com.nfb.medicalsupply;

import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.*;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyAdministratorServiceTests {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;

    @Autowired
    private CompanyAdministratorService companyAdministratorService;

    @Before
    public void setUp() throws Exception {

        Company c1 = new Company();
        c1.setName("Company 1");
        c1.setAddress("U-1-G-D");
        c1.setAverageRating(4.33);
        companyRepository.save(c1);

        Company c2 = new Company();
        c2.setName("Company 2");
        c2.setAddress("U-2-G-D");
        c2.setAverageRating(3.55);
        companyRepository.save(c2);

        CompanyAdministrator ca = new CompanyAdministrator();
        ca.setUsername("2001dn2001@gmail.com");
        ca.setPassword("12345678");
        ca.setName("Drasko");
        ca.setSurname("Novakovic");
        ca.setCity("Secanj");
        ca.setCountry("Srbija");
        ca.setOccupation("oc1");
        ca.setCompanyInfo("ci1");
        ca.setPhoneNumber("066123987");
        companyAdministratorRepository.save(ca);

    }

    @Test(expected = PessimisticLockingFailureException.class)
    public void testSetAvailableCAForNewCompany() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("\n\n\nStartovan Thread 1");
                //Company c = companyRepository.findById(1L).orElse(null);
                //try { Thread.sleep(3000); } catch (InterruptedException e) {}
                companyAdministratorService.setCompanyForAdministrator(1L, 1L);
                System.out.println("\n\n\nZavrsio thread 1");
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("\n\n\nStartovan Thread 2");
                //Company c = companyRepository.findById(2L).orElse(null);
                companyAdministratorService.setCompanyForAdministrator(1L, 2L);
                System.out.println("\n\n\nZavrsio thread 2");
            }
        });
        try {
            future2.get();
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
