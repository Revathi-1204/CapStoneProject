package com.mnp.donor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnp.donor.entity.RecipientSubscriber;
import com.mnp.donor.entity.Request;
import com.mnp.donor.service.RecipientSubscriberService;
import com.mnp.donor.service.RequestService;

@RestController
@CrossOrigin
@RequestMapping("/sub")
public class RecipientController {

    private final RequestService requestService;

    @Autowired
    public RecipientSubscriberService recipientSubscriberService;

    @Autowired
    public RecipientController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create")
    public Request createPortingRequest(@RequestBody Request request) {
        return requestService.createPortingRequest(request);
    }

    @GetMapping("/all")
    public List<Request> getAllPortingRequests() {
        return requestService.getAllPortingRequests();
    }

    @GetMapping("/getStatus/{mobileNumber}")
    public Request getSubscriberByNumber(@PathVariable String mobileNumber){
        return requestService.getSubscriberByMobileNumber(mobileNumber).get();
    }

    @GetMapping("/allSubscribers")
    public List<RecipientSubscriber> getAllSubscribers() {
        return recipientSubscriberService.getAllSubscriber();
    }

    @GetMapping("/activate/{number}")
    public RecipientSubscriber activateSubscriber(@PathVariable String number) {
        if(recipientSubscriberService.activateSubscriber(number)){
            return recipientSubscriberService.getSubscriber(number);
        }
        return null;
    }
    
}
