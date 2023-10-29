package com.mnp.donor.service;

import com.mnp.donor.entity.DonorSubscriber;
import com.mnp.donor.repository.DonorSubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpcServiceTests {

    private UpcService upcService;
    private DonorSubscriberRepository upcRepository;

    @BeforeEach
    void setUp() {
        upcRepository = Mockito.mock(DonorSubscriberRepository.class);
        upcService = new UpcService();
        upcService.upcRepository = upcRepository;
    }

    @Test
    void testVerificationExistingUser() {
        String existingNumber = "9123456789";
        DonorSubscriber existingUser = new DonorSubscriber();
        when(upcRepository.findByNumber(existingNumber)).thenReturn(Optional.of(existingUser));

        boolean result = upcService.verification(existingNumber);

        assertTrue(result);
    }

    @Test
    void testVerificationNonExistingUser() {
        String nonExistingNumber = "9876543210";
        when(upcRepository.findByNumber(nonExistingNumber)).thenReturn(Optional.empty());

        boolean result = upcService.verification(nonExistingNumber);

        assertFalse(result);
    }

    // @Test
    // void testCreateUserPortSuccess() {
    //     String number = "9493483882";
    //     UpcGen existingUser = new UpcGen();
    //     existingUser.setNumber(number);

    //     when(upcRepository.findByNumber(number)).thenReturn(Optional.of(existingUser));
    //     when(upcRepository.findByUpc(any())).thenReturn(Optional.empty());

    //     UpcGen result = upcService.createUserPort(number);

    //     assertNotNull(result);
    //     assertNotNull(result.getUpc());
    //     assertNotNull(result.getDate());
    // }

    @Test
    void testCreateUserPortInvalidNumber() {
        String invalidNumber = "123";
        when(upcRepository.findByNumber(invalidNumber)).thenReturn(Optional.empty());

        DonorSubscriber result = upcService.createUserPort(invalidNumber);

        assertNull(result);
    }

    @Test
    void testCreateUserPortNonUniqueUPC() {
        String number = "1234567890";
        DonorSubscriber existingUser = new DonorSubscriber();
        existingUser.setNumber(number);
        existingUser.setUpc("12345678");

        when(upcRepository.findByNumber(number)).thenReturn(Optional.of(existingUser));
        when(upcRepository.findByUpc("12345678")).thenReturn(Optional.of(existingUser));

        DonorSubscriber result = upcService.createUserPort(number);

        assertNull(result);
    }

    @Test
    void testGenerateRandomUPC() {
        String upc = upcService.generateRandomUPC();

        assertNotNull(upc);
        assertEquals(8, upc.length());
    }

    @Test
    void testIsUpcUnique() {
        String uniqueUPC = "12345678";
        when(upcRepository.findByUpc(uniqueUPC)).thenReturn(Optional.empty());

        boolean result = upcService.isUpcUnique(uniqueUPC);

        assertTrue(result);
    }

    @Test
    void testIsValidNumberValid() {
        String validNumber = "1234567890";

        boolean result = upcService.isValidNumber(validNumber);

        assertTrue(result);
    }

    @Test
    void testIsValidNumberInvalid() {
        String invalidNumber = "123";
        
        boolean result = upcService.isValidNumber(invalidNumber);

        assertFalse(result);
    }
}
