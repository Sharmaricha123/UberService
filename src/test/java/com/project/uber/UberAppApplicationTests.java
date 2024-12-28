package com.project.uber;

import com.project.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {

		emailSenderService.sendEmail(
				"powovek465@evnft.com",
				"This is the testing email",
				"Body of my email");
	}

	@Test
	void sendEmailMultiple(){
		String emails[]={
				"powovek465@evnft.com",
				"sharmarichaprof2017@gmail.com",
				"Sharmaritik14001@gmail.com"
		};

		emailSenderService.sendEmail(
				emails,
				"Hello from the UBER Application",
				"This is a Uber Application"
		);
	}

}
