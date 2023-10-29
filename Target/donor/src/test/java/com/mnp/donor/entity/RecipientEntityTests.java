package com.mnp.donor.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipientEntityTests{
    private RecipientSubscriber recipientSubscriber;

    @BeforeEach
    public void setUp() {
        recipientSubscriber = new RecipientSubscriber();
    }

    @Test
    public void testIdField() {
        recipientSubscriber.setId(1);
        assertEquals(1, recipientSubscriber.getId());
    }

    @Test
    public void testNameField() {
        recipientSubscriber.setName("John Doe");
        assertEquals("John Doe", recipientSubscriber.getName());
    }

    @Test
    public void testNumberField() {
        recipientSubscriber.setNumber("1234567890");
        assertEquals("1234567890", recipientSubscriber.getNumber());
    }

    @Test
    public void testDateField() {
        Date date = new Date();
        recipientSubscriber.setDate(date);
        assertEquals(date, recipientSubscriber.getDate());
    }

    @Test
    public void testAddressField() {
        recipientSubscriber.setAddress("123 Main St");
        assertEquals("123 Main St", recipientSubscriber.getAddress());
    }

}
