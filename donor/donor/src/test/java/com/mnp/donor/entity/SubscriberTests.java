package com.mnp.donor.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

public class SubscriberTests {

    private Request subscriber;

    @BeforeEach
    public void setUp() {
        subscriber = new Request();
    }

    @Test
    public void testId() {
        subscriber.setId(1);
        assertEquals(1, subscriber.getId());
    }

    @Test
    public void testName() {
        subscriber.setName("Revathi");
        assertEquals("Revathi", subscriber.getName());
    }

    @Test
    public void testNumber() {
        subscriber.setNumber("9123456789");
        assertEquals("9123456789", subscriber.getNumber());
    }

    @Test
    public void testDate() {
        Date testDate = new Date();
        subscriber.setDate(testDate);
        assertEquals(testDate, subscriber.getDate());
    }

    @Test
    public void testUpc() {
        subscriber.setUpc("24312123");
        assertEquals("24312123", subscriber.getUpc());
    }

    @Test
    public void testStatus() {
        subscriber.setStatus("Pending");
        assertEquals("Pending", subscriber.getStatus());
    }

    @Test
    public void testAddress() {
        subscriber.setAddress("Hyderabad");
        assertEquals("Hyderabad", subscriber.getAddress());
    }

    @Test
    public void testSubscriberNotNull() {
        assertNotNull(subscriber);
    }
}
