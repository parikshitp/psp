package com.divergentsl.acquirer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.divergentsl.acquirer.controller.AcquirerController;

@SpringBootTest
class AcquirerApplicationTests {

    @Autowired
    private AcquirerController acquirerController;

    @Test
    void contextLoads() {
        assertNotNull(acquirerController);
    }
}
