package com.mnp.donor.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnp.donor.entity.RecipientSubscriber;
import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.RecipientSubscriberRepository;
import com.mnp.donor.repository.RequestRepository;

import jakarta.transaction.Transactional;

@Service
public class RecipientSubscriberService {
    @Autowired RecipientSubscriberRepository recipientSubscriberRepository;

    @Autowired RequestRepository requestRepository;

    public List<RecipientSubscriber> getAllSubscriber(){
        return recipientSubscriberRepository.findAll();
    }

    public RecipientSubscriber getSubscriber(String number){
        return recipientSubscriberRepository.findByNumber(number).get();
    }

    @Transactional
    public boolean activateSubscriber(String number) {
        Optional<Request> optionalRequest = requestRepository.findByNumber(number);
        
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            if (request.getStatus() != null && request.getStatus().equalsIgnoreCase("CLEARED")) {
                RecipientSubscriber subscriber = new RecipientSubscriber();
                subscriber.setName(request.getName());
                subscriber.setNumber(request.getNumber());
                subscriber.setDate(new Date());
                subscriber.setAddress(request.getAddress());
                recipientSubscriberRepository.save(subscriber);
                requestRepository.deleteByNumber(number);
                return true;
            }
        }
        
        return false;
    }

}
