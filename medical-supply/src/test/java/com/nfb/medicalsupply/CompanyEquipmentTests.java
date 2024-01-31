package com.nfb.medicalsupply;

import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.*;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.CompanyEquipmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import com.nfb.modules.companies.core.usecases.CompanyEquipmentService;
import com.nfb.modules.companies.core.usecases.QRCodeService;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.UnexpectedRollbackException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyEquipmentTests {

    @Autowired
    private CompanyEquipmentRepository companyEquipmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private CompanyEquipmentService companyEquipmentService;

    @Before
    public void setUp() throws Exception {
        Equipment e = new Equipment();
        e.setName("Equipment");
        e.setType("T1");
        e.setDescription("D1");
        e.setPrice(10.0);
        equipmentRepository.save(e);

        Company c = new Company();
        c.setName("Company");
        c.setAddress("U-1-G-D");
        c.setAverageRating(4.33);
        companyRepository.save(c);

        CompanyEquipment ce = new CompanyEquipment();
        ce.setEquipment(e);
        ce.setCompany(c);
        ce.setQuantity(100);
        companyEquipmentRepository.save(ce);
    }

    @Test
    public void testStockRefreshing() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = executor.submit(() -> {
            System.out.println("Startovan Thread 1");
            var ret = companyEquipmentService.pickUpEquipment(1L, 1L, 20);
            System.out.println("Zavrsen Thread 1");
            return ret;
        });

        Future<Integer> future2 = executor.submit(() -> {
            System.out.println("Startovan Thread 2");
            var ret =  companyEquipmentService.pickUpEquipment(1L, 1L, 5);
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
            int result1 = future1.get();
            int result2 = future2.get();

            if ((result1 == 80 && result2 == 75) || (result1 == 75 && result2 == 95)) {
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
