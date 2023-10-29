package com.mnp.donor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.RequestRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request createPortingRequest(Request request) {
        if(verifyPorting(request)){
            request.setStatus("pending");
            return requestRepository.save(request);
        }
        request.setStatus("rejected");
        return requestRepository.save(request);
    }

    public List<Request> getAllPortingRequests() {
        return requestRepository.findAll();
    }

    public Request getPortingRequestById(int id) {
        return requestRepository.findById(id).orElse(null);
    }

    public void deletePortingRequest(int id) {
        requestRepository.deleteById(id);
    }

    public String getStatusByMobileNumber(String mobileNumber) {
        Request request = requestRepository.findByNumber(mobileNumber).get();
        if (request != null) {
            return request.getStatus();
        }
        return "Not Found"; 
    }

    public Optional<Request> getSubscriberByMobileNumber(String mobileNumber){
        return requestRepository.findByNumber(mobileNumber);
    }

    public boolean verifyUpc(String upc, Request request) {
        return request.getUpc().equals(upc);
    }

    // Checking if the user has ported in 90 days
    public boolean verifyPorting(Request request) {
        LocalDate currentDate = LocalDate.now();
        Date lastPortedDate = request.getDate();

        if (lastPortedDate != null) {
            LocalDate lastPortedLocalDate = lastPortedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ninetyDaysAgo = currentDate.minusDays(90);
           
            if (lastPortedLocalDate.isAfter(ninetyDaysAgo)) {
                return true;
            }
        }

        else if (request.getStatus().equals("pending")) {  
            return false;
        }

        return true;
    }


    
}
