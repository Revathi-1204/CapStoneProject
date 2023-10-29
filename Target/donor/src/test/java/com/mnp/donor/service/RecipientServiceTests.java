package com.mnp.donor.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.mnp.donor.entity.RecipientSubscriber;
import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.RecipientSubscriberRepository;
import com.mnp.donor.repository.RequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RecipientServiceTests {

    @Mock
    private RecipientSubscriberRepository recipientSubscriberRepository;

    @Mock
    private RequestRepository requestRepository;

    private RecipientSubscriberService recipientSubscriberService;

    @BeforeEach
    public void setUp() {
        recipientSubscriberService = new RecipientSubscriberService();
        recipientSubscriberService.recipientSubscriberRepository = recipientSubscriberRepository;
        recipientSubscriberService.requestRepository = requestRepository;
    }

    @Test
    public void testGetAllSubscriber() {
        // Create a list of sample subscribers
        List<RecipientSubscriber> subscribers = new ArrayList<>();
        subscribers.add(new RecipientSubscriber());
        subscribers.add(new RecipientSubscriber());

        // Mock the behavior of the recipientSubscriberRepository
        Mockito.when(recipientSubscriberRepository.findAll()).thenReturn(subscribers);

        // Test the getAllSubscriber method
        List<RecipientSubscriber> result = recipientSubscriberService.getAllSubscriber();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetSubscriber() {
        // Create a sample subscriber
        RecipientSubscriber subscriber = new RecipientSubscriber();
        subscriber.setNumber("1234567890");

        // Mock the behavior of the recipientSubscriberRepository
        Mockito.when(recipientSubscriberRepository.findByNumber("1234567890")).thenReturn(Optional.of(subscriber));

        // Test the getSubscriber method
        RecipientSubscriber result = recipientSubscriberService.getSubscriber("1234567890");
        assertNotNull(result);
        assertEquals("1234567890", result.getNumber());
    }

    @Test
    public void testActivateSubscriber() {
        // Create a sample request
        Request request = new Request();
        request.setNumber("1234567890");
        request.setStatus("CLEARED");
        request.setName("John Doe");
        request.setAddress("123 Main St");

        // Mock the behavior of the requestRepository
        Mockito.when(requestRepository.findByNumber("1234567890")).thenReturn(Optional.of(request));

        // Test the activateSubscriber method
        boolean activated = recipientSubscriberService.activateSubscriber("1234567890");
        assertTrue(activated);

        // Verify that the recipientSubscriberRepository and requestRepository were called as expected
        Mockito.verify(recipientSubscriberRepository).save(Mockito.any(RecipientSubscriber.class));
        Mockito.verify(requestRepository).deleteByNumber("1234567890");
    }

    @Test
    public void testActivateSubscriberWithNonClearedRequest() {
        // Create a sample request with a non-CLEARED status
        Request request = new Request();
        request.setNumber("1234567890");
        request.setStatus("PENDING");
        request.setName("John Doe");
        request.setAddress("123 Main St");

        // Mock the behavior of the requestRepository
        Mockito.when(requestRepository.findByNumber("1234567890")).thenReturn(Optional.of(request));

        // Test the activateSubscriber method with a non-CLEARED request
        boolean activated = recipientSubscriberService.activateSubscriber("1234567890");
        assertFalse(activated);
    }

    @Test
    public void testActivateSubscriberWithNonExistingRequest() {
        // Mock the behavior of the requestRepository when the request doesn't exist
        Mockito.when(requestRepository.findByNumber("9123456789")).thenReturn(Optional.empty());

        // Test the activateSubscriber method with a non-existing request
        boolean activated = recipientSubscriberService.activateSubscriber("9123456789");
        assertFalse(activated);
    }
}
