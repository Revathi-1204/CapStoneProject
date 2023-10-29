package com.mnp.donor.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VerificationCases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    private boolean isApproved;

    private int outStandingPayments;

    private Date date;

    private boolean isCorporate;

    private boolean isAuthorized;

    private String contractualStatus; //cleared or not

    private boolean isProhibited;

    private String subJudice; //legal or illegal

    private String ownershipStatus;//pending or not

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public int getOutStandingPayments() {
        return outStandingPayments;
    }

    public void setOutStandingPayments(int outStandingPayments) {
        this.outStandingPayments = outStandingPayments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCorporate() {
        return isCorporate;
    }

    public void setCorporate(boolean isCorporate) {
        this.isCorporate = isCorporate;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public String getContractualStatus() {
        return contractualStatus;
    }

    public void setContractualStatus(String contractualStatus) {
        this.contractualStatus = contractualStatus;
    }

    public boolean isProhibited() {
        return isProhibited;
    }

    public void setProhibited(boolean isProhibited) {
        this.isProhibited = isProhibited;
    }

    public String getSubJudice() {
        return subJudice;
    }

    public void setSubJudice(String subJudice) {
        this.subJudice = subJudice;
    }

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public void setOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }
    
}
