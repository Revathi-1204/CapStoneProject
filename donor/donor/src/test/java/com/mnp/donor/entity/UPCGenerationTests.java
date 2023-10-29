package com.mnp.donor.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

@SpringBootTest
public class UPCGenerationTests {

    private DonorSubscriber upcGen;

    @BeforeEach
    public void setUp() {
        upcGen = new DonorSubscriber();
    }

    @Test
    public void testSetAndGetId() {
        upcGen.setId(1);
        assertEquals(1, upcGen.getId());
    }

    @Test
    public void testSetAndGetNumber() {
        upcGen.setNumber("123456789");
        assertEquals("123456789", upcGen.getNumber());
    }

    @Test
    public void testSetAndGetUpc() {
        upcGen.setUpc("UPC123");
        assertEquals("UPC123", upcGen.getUpc());
    }

    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        upcGen.setDate(date);
        assertEquals(date, upcGen.getDate());
    }
}
