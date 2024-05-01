package com.divergentsl.psp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.divergentsl.psp.controller.PspController;

@SpringBootTest
class PSPApplicationTests {

	@Autowired
	private PspController pspController;

	@Test
	void contextLoads() {
		assertNotNull(pspController);
	}
}