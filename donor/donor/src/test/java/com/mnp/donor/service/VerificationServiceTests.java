package com.mnp.donor.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mnp.donor.entity.VerificationCases;
import com.mnp.donor.entity.DonorSubscriber;
import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.DonorSubscriberRepository;
import com.mnp.donor.repository.RequestRepository;
import com.mnp.donor.repository.VerificationRepository;

public class VerificationServiceTests {
    @Mock
    private VerificationRepository verificationRepository;

    @Mock
    private DonorSubscriberRepository donorSubscriberRepository;

    @Mock
    private RequestRepository requestRepository;

    private VerificationService verificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        verificationService = new VerificationService();
        verificationService.verificationRepository = verificationRepository;
        verificationService.donorSubRepository = donorSubscriberRepository;
        verificationService.requestRepository = requestRepository;
        Request request = new Request(); // Initialize request here
        verificationService.request = request;
    }

    @Test
    void testVerifyOutstandingPayments() {
        VerificationCases verification = new VerificationCases();
        verification.setOutStandingPayments(0);
        verificationService.request = new Request(); // Ensure request is initialized
        boolean result = verificationService.verifyOutstandingPayments(verification);
        assertTrue(result);

        verification.setOutStandingPayments(100);
        verificationService.request = new Request(); // Ensure request is initialized
        result = verificationService.verifyOutstandingPayments(verification);
        assertFalse(result);
        assertEquals("Outstanding payments dues", verificationService.request.getRejectionReason());
    }

    @Test
    void testVerifyNinetyDaysElapsedLessThan90Days() {
        VerificationCases verification = new VerificationCases();
        LocalDate activationDate = LocalDate.now().minusDays(89); // Less than 90 days
        verification.setDate(Date.from(activationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        verificationService.request = new Request(); // Ensure request is initialized
        boolean result = verificationService.verifyNinetyDaysElapsed(verification);
        assertFalse(result);
    }

    @Test
    void testVerifyNinetyDaysElapsedMoreThan90Days() {
        VerificationCases verification = new VerificationCases();
        LocalDate activationDate = LocalDate.now().minusDays(100); // More than 90 days
        verification.setDate(Date.from(activationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        verificationService.request = new Request(); // Ensure request is initialized
        boolean result = verificationService.verifyNinetyDaysElapsed(verification);
        assertFalse(result);
        assertEquals("Period of Ninety days has not elapsed", verificationService.request.getRejectionReason());
    }


    @Test
    void testVerifyOwnershipStatus() {
        VerificationCases verification = new VerificationCases();
        verification.setOwnershipStatus("approved");
        boolean result = verificationService.verifyOwnershipStatus(verification);
        assertTrue(result);

        verification.setOwnershipStatus("pending");
        result = verificationService.verifyOwnershipStatus(verification);
        assertFalse(result);
        assertEquals("Change of Mobile Number Ownership is in process", verificationService.request.getRejectionReason());
    }

    @Test
    void testVerifyIsSubJudice() {
        VerificationCases verification = new VerificationCases();
        verification.setSubJudice("legal");
        boolean result = verificationService.verifyIsSubJudice(verification);
        assertTrue(result);

        verification.setSubJudice("illegal");
        boolean resultf = verificationService.verifyIsSubJudice(verification);
        assertFalse(resultf);
        assertEquals("Mobile Number is under sub-judice", verificationService.request.getRejectionReason());
    }




    @Test
    void testVerifyCorporateNumber() {
        VerificationCases verification = new VerificationCases();
        verification.setCorporate(true);
        verification.setAuthorized(true);
        boolean result = verificationService.verifyCorporateNumber(verification);
        assertTrue(result);

        verification.setAuthorized(false);
        result = verificationService.verifyCorporateNumber(verification);
        assertFalse(result);
        assertEquals("Unauthorized corporate number", verificationService.request.getRejectionReason());

        verification.setCorporate(false);
        result = verificationService.verifyCorporateNumber(verification);
        assertTrue(result);
    }

    @Test
    void testVerifyContractualObligation() {
        VerificationCases verification = new VerificationCases();
        verification.setContractualStatus("Cleared");
        boolean result = verificationService.verifyContractualObligation(verification);
        assertTrue(result);

        verification.setContractualStatus("Uncleared");
        result = verificationService.verifyContractualObligation(verification);
        assertFalse(result);
        assertEquals("Contractual Obligation not cleared", verificationService.request.getRejectionReason());
    }

}
