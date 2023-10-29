package com.mnp.donor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mnp.donor.entity.Request;
import com.mnp.donor.entity.VerificationCases;
import com.mnp.donor.repository.DonorSubscriberRepository;
import com.mnp.donor.repository.RequestRepository;
import com.mnp.donor.repository.VerificationRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MnpspServiceTests {
    @InjectMocks
    private MnspspService mnspspService;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private DonorSubscriberRepository donorSubscriberRepository;

    @Mock
    private VerificationRepository verificationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPortingRequestById() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        int requestId = 1;
        Request expectedRequest = new Request();
        when(requestRepository.findById(requestId)).thenReturn(Optional.of(expectedRequest));

        Request result = mnspspService.getPortingRequestById(requestId);

        assertEquals(expectedRequest, result);
    }

    @Test
    void testDeletePortingRequest() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        String mobileNumber = "1234567890";

        mnspspService.deletePortingRequest(mobileNumber);

        verify(donorSubscriberRepository).deleteByNumber(mobileNumber);
    }

    @Test
    void testGetSubscriberByMobileNumber() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        String mobileNumber = "1234567890";
        Request expectedRequest = new Request();
        when(requestRepository.findByNumber(mobileNumber)).thenReturn(Optional.of(expectedRequest));

        Optional<Request> result = mnspspService.getSubscriberByMobileNumber(mobileNumber);

        assertEquals(expectedRequest, result.orElse(null));
    }

    @Test
    void testVerifyUpc() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        Request subscriber = new Request();
        subscriber.setName("9876543210");
        subscriber.setUpc("12345678");
        String validUpc = "12345678";
        String invalidUpc = "12345679";

        assertTrue(mnspspService.verifyUpc(validUpc, subscriber));
        assertFalse(mnspspService.verifyUpc(invalidUpc, subscriber));
    }

    @Test
    void testGetDonorByNumber() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        Request subscriber = new Request();
        subscriber.setNumber("9876543210");
        String number = "9876543210";
        VerificationCases expectedDonor = new VerificationCases();
        when(requestRepository.findByNumber(number)).thenReturn(Optional.of(subscriber));
        when(verificationRepository.findByNumber(number)).thenReturn(Optional.of(expectedDonor));

        VerificationCases result = mnspspService.getDonorByNumber(subscriber);

        assertEquals(expectedDonor, result);
    }

    @Test
    void testGetAllRequests() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        List<Request> expectedRequests = Arrays.asList(new Request(), new Request(), new Request());
        when(requestRepository.findAll()).thenReturn(expectedRequests);

        List<Request> result = mnspspService.getAllRequests();

        assertEquals(expectedRequests, result);
    }

    @Test
    void testGetClearedRequests() {
        MnspspService mnspspService = new MnspspService(requestRepository, donorSubscriberRepository, verificationRepository);
        List<Request> expectedClearedRequests = Arrays.asList(new Request(), new Request());
        when(requestRepository.findByStatus("CLEARED")).thenReturn(expectedClearedRequests);

        List<Request> result = mnspspService.getClearedRequests();

        assertEquals(expectedClearedRequests, result);
    }
}
