package com.mnp.donor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnp.donor.entity.VerificationCases;
import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.VerificationRepository;

import jakarta.transaction.Transactional;

import com.mnp.donor.repository.DonorSubscriberRepository;
import com.mnp.donor.repository.RequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MnspspService {
    
    private final RequestRepository requestRepository;
    private final DonorSubscriberRepository donorSubscriberRepository;

    private final VerificationRepository verificationRepository;

    @Autowired
    public MnspspService(
            RequestRepository requestRepository, 
            DonorSubscriberRepository donorSubscriberRepository,
            VerificationRepository verificationRepository) {
        this.requestRepository = requestRepository;
        this.donorSubscriberRepository = donorSubscriberRepository;
        this.verificationRepository = verificationRepository;
    }
    

    public Request getPortingRequestById(int id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deletePortingRequest(String number) {
        donorSubscriberRepository.deleteByNumber(number);
    }

    public Optional<Request> getSubscriberByMobileNumber(String mobileNumber){
        return requestRepository.findByNumber(mobileNumber);
    }

    public boolean verifyUpc(String upc, Request subscriber) {
        return subscriber.getUpc().equals(upc);
    }

    public VerificationCases getDonorByNumber(Request subscriber){
        String number = requestRepository.findByNumber(subscriber.getNumber()).get().getNumber();
        return verificationRepository.findByNumber(number).get();
    }

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    public List<Request> getClearedRequests(){
        return requestRepository.findByStatus("CLEARED");
    }
    
}
