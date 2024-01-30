package com.nfb.medicalsupply;

import com.nfb.modules.companies.API.dtos.QRCodeDto;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.usecases.QRCodeService;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
class MedicalSupplyApplicationTests {

	@Autowired
	private QRCodeService qrCodeService;

	@Before
	public void setUp() throws Exception {
		Role role = new Role((long)0, "REGISTERED_USER");
		RegisteredUser ru = new RegisteredUser("2001sk2001@gmail.com", "12345678", role, "Stefan",
											 "Kovacevic","Gacko", "BiH, RS", "066789654",
											"oc1", "ci1", 0);
		qrCodeService.addQRCodeFromDto(new QRCodeDto(new QRCode()));
	}

}
