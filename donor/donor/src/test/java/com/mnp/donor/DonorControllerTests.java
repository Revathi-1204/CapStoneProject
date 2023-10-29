package com.mnp.donor;

import com.mnp.donor.controller.DonorController;
import com.mnp.donor.entity.VerificationCases;
import com.mnp.donor.entity.Request;
import com.mnp.donor.entity.DonorSubscriber;
import com.mnp.donor.service.VerificationService;
import com.mnp.donor.service.MnspspService;
import com.mnp.donor.service.UpcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DonorControllerTests {

    private DonorController donorController;
    private UpcService upcService;
    private VerificationService donorService;
    private MnspspService mnspspService;

    @BeforeEach
    void setUp() {
        upcService = Mockito.mock(UpcService.class);
        donorService = Mockito.mock(VerificationService.class);
        mnspspService = Mockito.mock(MnspspService.class);
        donorController = new DonorController(upcService);
        donorController.verificationService = donorService;
        donorController.mnspspService = mnspspService;
    }

    @Test
    void testCreateUserPortSuccess() {
        String number = "9123456789";
        String upc = "23415123";

        DonorSubscriber upcGen = new DonorSubscriber();
        upcGen.setUpc(upc);

        when(upcService.createUserPort(number)).thenReturn(upcGen);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("number", number);

        ResponseEntity<?> response = donorController.createUserPort(requestBody);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("upc", upc), response.getBody());
    }

    @Test
    void testCreateUserPortFailure() {
        String number = "invalid";

        when(upcService.createUserPort(number)).thenReturn(null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("number", number);

        ResponseEntity<?> response = donorController.createUserPort(requestBody);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Phone number is not valid or does not exists.", response.getBody());
    }

    @Test
    void testGetDonorByNumber() {
        String number = "9123456789";

        Request subscriber = new Request();
        subscriber.setNumber(number);

        VerificationCases donor = new VerificationCases();
        donor.setNumber(number);

        when(mnspspService.getSubscriberByMobileNumber(number)).thenReturn(Optional.of(subscriber));
        when(mnspspService.getDonorByNumber(subscriber)).thenReturn(donor);

    }
}
