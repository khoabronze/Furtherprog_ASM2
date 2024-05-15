package com.example.furtherprog_asm2;

/**
 * @author <Dong Dang Khoa- s3986281>
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
}

