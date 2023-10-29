package com.mnp.donor.service;


import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.RequestRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriberServiceTests {

    private RequestService requestService;
    private RequestRepository requestRepository;

    @BeforeEach
    public void setUp() {
        requestRepository = Mockito.mock(RequestRepository.class);
        requestService = new RequestService(requestRepository);
    }

    // @Test
    // public void testCreatePortingRequest() {
    //     Subscriber subscriber = new Subscriber();
    //     subscriber.setName("Revathi");
    //     subscriber.setStatus("pending");

    //     when(subscriberRepository.save(any(Subscriber.class))).thenReturn(subscriber);

    //     Subscriber result = subscriberService.createPortingRequest(subscriber);

    //     assertEquals("pending", result.getStatus());
    // }


    @Test
    public void testGetAllPortingRequests() {
        List<Request> subscribers = new ArrayList<>();
        subscribers.add(new Request());
        subscribers.add(new Request());

        when(requestRepository.findAll()).thenReturn(subscribers);

        List<Request> result = requestService.getAllPortingRequests();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetPortingRequestById() {
        int id = 1;
        Request subscriber = new Request();
        subscriber.setId(id);

        when(requestRepository.findById(id)).thenReturn(Optional.of(subscriber));

        Request result = requestService.getPortingRequestById(id);

        assertEquals(id, result.getId());
    }

    @Test
    public void testDeletePortingRequest() {
        int id = 1;

        requestService.deletePortingRequest(id);

        Mockito.verify(requestRepository).deleteById(id);
    }

    @Test
    public void testGetStatusByMobileNumber() {
        String mobileNumber = "1234567890";
        Request subscriber = new Request();
        subscriber.setNumber(mobileNumber);
        subscriber.setStatus("pending");

        when(requestRepository.findByNumber(mobileNumber)).thenReturn(Optional.of(subscriber));

        String result = requestService.getStatusByMobileNumber(mobileNumber);

        assertEquals("pending", result);
    }

    @Test
    public void testGetSubscriberByMobileNumber() {
        String mobileNumber = "9123456789";
        Request subscriber = new Request();
        subscriber.setNumber(mobileNumber);

        when(requestRepository.findByNumber(mobileNumber)).thenReturn(Optional.of(subscriber));

        Optional<Request> result = requestService.getSubscriberByMobileNumber(mobileNumber);

        assertEquals(mobileNumber, result.get().getNumber());
    }

    @Test
    public void testVerifyUpc() {
        String upc = "21345123";
        Request subscriber = new Request();
        subscriber.setUpc(upc);

        boolean result = requestService.verifyUpc(upc, subscriber);

        assertEquals(true, result);
    }

    @Test
    public void testVerifyPortingWhenNotPending() {
        Request subscriber = new Request();
        subscriber.setDate(new Date());
        subscriber.setStatus("rejected");

        boolean result = requestService.verifyPorting(subscriber);

        assertEquals(true, result);
    }

    @Test
    public void testVerifyPortingWhenPending() {
        Request subscriber = new Request();
        subscriber.setStatus("pending");

        boolean result = requestService.verifyPorting(subscriber);

        assertEquals(false, result);
    }

    @Test
    public void testVerifyPortingWhenNoLastPortedDate() {
        Request subscriber = new Request();
        subscriber.setStatus("active");

        boolean result = requestService.verifyPorting(subscriber);

        assertEquals(true, result);
    }
}
