package com.mnp.donor.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnp.donor.entity.VerificationCases;
import com.mnp.donor.entity.Request;
import com.mnp.donor.repository.VerificationRepository;
import com.mnp.donor.repository.DonorSubscriberRepository;
import com.mnp.donor.repository.RequestRepository;

@Service
public class VerificationService {
    @Autowired VerificationRepository verificationRepository;

    @Autowired DonorSubscriberRepository donorSubRepository;
    @Autowired RequestRepository requestRepository;

    public Request request;

    public void processPortingRequest(Request request) {
        this.request = request;
        Optional<VerificationCases> verificationOpt = verificationRepository.findByNumber(this.request.getNumber());
    
        if (verificationOpt.isPresent()) {
            VerificationCases verification = verificationOpt.get();
            boolean isCleared = verifyClearance(verification);
    
            if (isCleared) {
                this.request.setRejectionReason(null);
                this.request.setStatus("CLEARED");
                requestRepository.save(this.request);
            } else {
                this.request.setStatus("REJECTED");
                requestRepository.save(this.request);
            }
        } else {
            // Handle the case when no verification data is found
            this.request.setStatus("REJECTED");
            this.request.setRejectionReason("Verification data not found");
            requestRepository.save(this.request);
        }
    }
    
    

    // Methods to verify clearance based on grounds 

    // Outstanding payments due for the issued bill
    public boolean verifyOutstandingPayments(VerificationCases verfication) {
        if (verfication.getOutStandingPayments() > 0) {
            this.request.setRejectionReason("Outstanding payments dues");
            return false;
        }
        return true;
    } 

    //Period of ninety days has not elapsed
    public boolean verifyNinetyDaysElapsed(VerificationCases verification) {
        Date date = verification.getDate();
        if (date == null) {
            this.request.setRejectionReason("Activation date is missing");
            return false;
        }
        LocalDate activationDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
    
        Period daysElapsed = Period.between(activationDate, currentDate);
        if (daysElapsed.getDays() < 90) { // Change to greater than 90
            this.request.setRejectionReason("Period of Ninety days has not elapsed");
            return false;
        }
        return true; // Return true if less than or equal to 90 days
    }
    

    // Change of ownership of mobile number is under process
    public boolean verifyOwnershipStatus(VerificationCases verification){
        if(verification.getOwnershipStatus().equalsIgnoreCase("pending")){
            this.request.setRejectionReason("Change of Mobile Number Ownership is in process");
            return false;
        }
        return true;
    }

    //The mobile number is sub-judice
    public boolean verifyIsSubJudice(VerificationCases verification){
        if(verification.getSubJudice().equalsIgnoreCase("legal")){
            return true;
        }
        this.request.setRejectionReason("Mobile Number is under sub-judice");
        return false;
    }

    //Prohibited by a Court of Law
    public boolean verfiyIsProhibitedByLaw(VerificationCases verification){
        if(!verification.isProhibited()){
            return true;
        }
        this.request.setRejectionReason("Mobile Number prohibited by Court of Law");
        return false;
    }

    //Validity of UPC has expired or not
    public boolean verifyUPCValidity(VerificationCases verification) {
        Date date = donorSubRepository.findByNumber(verification.getNumber()).get().getDate();
        LocalDate activationDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(activationDate, currentDate);
        if (period.getDays() <= 4) {
            return true; 
        } 
        this.request.setRejectionReason("UPC validity has expired");
        return false;
    }

    // UPC mismatch
    public boolean verifyUPC(VerificationCases verification) {
        String upcGen =  donorSubRepository.findByNumber(verification.getNumber()).get().getUpc();
        if(upcGen.equals(this.request.getUpc())){
            return true;
        }
        this.request.setRejectionReason("UPC does not match");
        return false;
    }

    // In case of a corporate mobile number, the porting request is not accompanied by authorization letter from authorized signatory
    public boolean verifyCorporateNumber(VerificationCases verification) {
        if (verification.isCorporate() && !verification.isAuthorized()) {
            this.request.setRejectionReason("Unauthorized corporate number");
            return false;
        }
        return true;
    }    

    // Contractual obligation not cleared by the subscriber before porting
    public boolean verifyContractualObligation(VerificationCases verification){
        if(verification.getContractualStatus().equalsIgnoreCase("Cleared")){
            return true;
        }
        this.request.setRejectionReason("Contractual Obligation not cleared");
        return false;
    }

    //the overall clearance
    public boolean verifyClearance(VerificationCases verification) {
        return verifyOutstandingPayments(verification) &&
               verifyUPC(verification)&&
               verifyUPCValidity(verification)&&
               verfiyIsProhibitedByLaw(verification)&&
               verifyContractualObligation(verification)&&
               verifyCorporateNumber(verification)&&
               verifyNinetyDaysElapsed(verification) &&
               verifyIsSubJudice(verification);
    }


}
