package com.mnp.donor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnp.donor.controller.RecipientController;
import com.mnp.donor.entity.Request;
import com.mnp.donor.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubscriberControllerTests{

    private RecipientController recipientController;
    private RequestService requestService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        requestService = Mockito.mock(RequestService.class);
        recipientController = new RecipientController(requestService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipientController).build();
    }

    @Test
    void testCreatePortingRequest() throws Exception {
        Request subscriber = new Request();
        subscriber.setName("John Doe");
        subscriber.setStatus("pending");

        when(requestService.createPortingRequest(any(Request.class))).thenReturn(subscriber);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/sub/create")
                .content(new ObjectMapper().writeValueAsString(subscriber))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllPortingRequests() throws Exception {
        List<Request> subscribers = new ArrayList<>();
        subscribers.add(new Request());
        subscribers.add(new Request());

        when(requestService.getAllPortingRequests()).thenReturn(subscribers);

        mockMvc.perform(MockMvcRequestBuilders.get("/sub/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetSubscriberByNumber() throws Exception {
        String mobileNumber = "9123456789";
        Request subscriber = new Request();
        subscriber.setNumber(mobileNumber);

        when(requestService.getSubscriberByMobileNumber(mobileNumber)).thenReturn(Optional.of(subscriber));

        mockMvc.perform(MockMvcRequestBuilders.get("/sub/getStatus/" + mobileNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
}
