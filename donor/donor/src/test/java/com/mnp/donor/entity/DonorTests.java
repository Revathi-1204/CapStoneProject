package com.mnp.donor.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class DonorTests {

    @Test
    void testId() {
        VerificationCases donor = new VerificationCases();
        donor.setId(1);
        assertEquals(1, donor.getId());
    }


    @Test
    void testNumber() {
        VerificationCases donor = new VerificationCases();
        donor.setNumber("1234567890");
        assertEquals("1234567890", donor.getNumber());
    }


    @Test
    void testApproved() {
        VerificationCases donor = new VerificationCases();
        donor.setApproved(true);
        assertTrue(donor.isApproved());
    }

    @Test
    void testOutStandingPayments() {
        VerificationCases donor = new VerificationCases();
        donor.setOutStandingPayments(5);
        assertEquals(5, donor.getOutStandingPayments());
    }

    @Test
    void testDate() {
        VerificationCases donor = new VerificationCases();
        Date date = new Date();
        donor.setDate(date);
        assertEquals(date, donor.getDate());
    }

    @Test
    void testCorporate() {
        VerificationCases donor = new VerificationCases();
        donor.setCorporate(true);
        assertTrue(donor.isCorporate());
    }

    @Test
    void testAuthorized() {
        VerificationCases donor = new VerificationCases();
        donor.setAuthorized(false);
        assertFalse(donor.isAuthorized());
    }

    @Test
    void testContractualStatus() {
        VerificationCases donor = new VerificationCases();
        donor.setContractualStatus("cleared");
        assertEquals("cleared", donor.getContractualStatus());
    }

    @Test
    void testProhibited() {
        VerificationCases donor = new VerificationCases();
        donor.setProhibited(true);
        assertTrue(donor.isProhibited());
    }

    @Test
    void testSubJudice() {
        VerificationCases donor = new VerificationCases();
        donor.setSubJudice("legal");
        assertEquals("legal", donor.getSubJudice());
    }

    @Test
    void testOwnershipStatus() {
        VerificationCases donor = new VerificationCases();
        donor.setOwnershipStatus("pending");
        assertEquals("pending", donor.getOwnershipStatus());
    }

    @Test
    void testDefaultConstructor() {
        VerificationCases donor = new VerificationCases();
        assertNotNull(donor);
        assertEquals(0, donor.getId());
        assertNull(donor.getNumber());
        assertFalse(donor.isApproved());
        assertEquals(0, donor.getOutStandingPayments());
        assertNull(donor.getDate());
        assertFalse(donor.isCorporate());
        assertFalse(donor.isAuthorized());
        assertNull(donor.getContractualStatus());
        assertFalse(donor.isProhibited());
        assertNull(donor.getSubJudice());
        assertNull(donor.getOwnershipStatus());
    }


    
}