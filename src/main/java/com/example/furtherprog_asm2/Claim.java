package com.example.furtherprog_asm2;

/**
 * @author <Dong Dang Khoa- s3986281>
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.time.LocalDate;

public class Claim {
    private String id;
    private Date claimDate;
    private String InsuredPerson;
    private String cardNumber;
    private Date examDate;
    private String documents;
    private double claimAmount;
    private ClaimStatus status;
    private BankingInfo reiveBankingInfo;

    public Claim() {
        this.id = id;
        this.claimDate = new Date();
        InsuredPerson = "Default";
        this.cardNumber = "default";
        this.examDate = new Date();
        this.documents = "Default";
        this.claimAmount = 0.00;
        this.status = null;
        this.reiveBankingInfo = reiveBankingInfo;
    }
    public Claim(String id, Date claimDate, String insuredPerson, String cardNumber, Date examDate, String documents, double claimAmount, ClaimStatus status, BankingInfo reiveBankingInfo) {
        this.id = id;
        this.claimDate = new Date();
        InsuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = new Date();
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.reiveBankingInfo = reiveBankingInfo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getInsuredPerson() {
        return InsuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        InsuredPerson = insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public BankingInfo getReiveBankingInfo() {
        return reiveBankingInfo;
    }

    public void setReiveBankingInfo(BankingInfo reiveBankingInfo) {
        this.reiveBankingInfo = reiveBankingInfo;
    }


    @Override
    public String toString() {
        return "Claim{" +
                "id='" + id + '\'' +
                ", claimDate=" + claimDate +
                ", InsuredPerson='" + InsuredPerson + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", examDate=" + examDate +
                ", documents=" + documents +
                ", claimAmount=" + claimAmount +
                ", status=" + status +
                ", reiveBankingInfo=" + reiveBankingInfo +
                '}';
    }
    // for testing
    private int claimId;
    private int policyHolderId;
    private int dependentId;
    private double amount;
    private LocalDate dateOfClaim;
    private String description;

    // Constructor
    public Claim(int claimId, int policyHolderId, int dependentId, double amount, LocalDate dateOfClaim, String description) {
        this.claimId = claimId;
        this.policyHolderId = policyHolderId;
        this.dependentId = dependentId;
        this.amount = amount;
        this.dateOfClaim = dateOfClaim;
        this.description = description;
    }

    // Getter and Setter methods
    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(int policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public int getDependentId() {
        return dependentId;
    }

    public void setDependentId(int dependentId) {
        this.dependentId = dependentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateOfClaim() {
        return dateOfClaim;
    }

    public void setDateOfClaim(LocalDate dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

