package com.mnp.donor.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

public class SubscriberTests {

    private Request request;

    @BeforeEach
    public void setUp() {
        request = new Request();
    }

    @Test
    public void testId() {
        request.setId(1);
        assertEquals(1, request.getId());
    }

    @Test
    public void testName() {
        request.setName("Revathi");
        assertEquals("Revathi", request.getName());
    }

    @Test
    public void testNumber() {
        request.setNumber("9123456789");
        assertEquals("9123456789", request.getNumber());
    }

    @Test
    public void testDate() {
        Date testDate = new Date();
        request.setDate(testDate);
        assertEquals(testDate, request.getDate());
    }

    @Test
    public void testUpc() {
        request.setUpc("24312123");
        assertEquals("24312123", request.getUpc());
    }

    @Test
    public void testStatus() {
        request.setStatus("Pending");
        assertEquals("Pending", request.getStatus());
    }

    @Test
    public void testAddress() {
        request.setAddress("Hyderabad");
        assertEquals("Hyderabad", request.getAddress());
    }

    @Test
    public void testSubscriberNotNull() {
        assertNotNull(request);
    }
}
