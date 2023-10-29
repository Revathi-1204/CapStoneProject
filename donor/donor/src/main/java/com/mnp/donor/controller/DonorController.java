package com.mnp.donor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mnp.donor.entity.VerificationCases;
import com.mnp.donor.entity.Request;
import com.mnp.donor.dto.Responsedto;
import com.mnp.donor.entity.DonorSubscriber;
import com.mnp.donor.service.VerificationService;
import com.mnp.donor.service.MnspspService;
import com.mnp.donor.service.UpcService;

@RestController
@CrossOrigin
@RequestMapping("/donor")
public class DonorController {
    
    @Autowired
    public VerificationService verificationService;

    @Autowired
    public MnspspService mnspspService;

    private final UpcService upcService;

    @Autowired
    public DonorController(UpcService upcService) {
        this.upcService = upcService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserPort(@RequestBody Map<String, String> requestBody) {
        String number = requestBody.get("number");
        DonorSubscriber createdUserPort = upcService.createUserPort(number);
        if (createdUserPort != null) {
            String upc = createdUserPort.getUpc();
            return ResponseEntity.ok(Map.of("upc", upc));
        } else {
            return ResponseEntity.badRequest().body("Phone number is not valid or does not exists.");
        }
    }
    @GetMapping("/get/{number}")
    public Responsedto getDonorByNumber(@PathVariable String number){
        Request request = mnspspService.getSubscriberByMobileNumber(number).get();
        verificationService.processPortingRequest(request);
        Responsedto resp = new Responsedto();
        resp.setStatus(request.getStatus());
        resp.setReason(request.getRejectionReason());
        return resp;

    }
    @DeleteMapping("/delete/{number}")
    public void deletePortingRequest(@PathVariable String number) {
        mnspspService.deletePortingRequest(number);
    }

    @GetMapping("/getcleared")
    public List<Request> getcleared(){
        return mnspspService.getClearedRequests();
    }

    @GetMapping("/allrequests")
    public List<Request> getAllRequests() {
        List<Request> requests = mnspspService.getAllRequests();
        return requests;
    }

    @GetMapping("/forward/{number}")
    public void Verification(@PathVariable String number){
        this.getDonorByNumber(number);
    }

}

