package com.example.furtherprog_asm2;

import java.util.ArrayList;

class Dependent extends User {
    private PolicyHolder policyHolder;

    // Default Constructor
    public Dependent() {
        super();
        this.setRole("Dependent");
    }

    // Constructor with policyHolder
    public Dependent(PolicyHolder policyHolder) {
        super();
        this.policyHolder = policyHolder;
        this.setRole("Dependent");
    }

    // Constructor with parameters, ensuring role is set to "Dependent"
    public Dependent(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, ArrayList<Claim> claims) {
        super(id, name, phone, email, address, password, insuranceCard, claims, "Dependent");
    }

    // Getters and Setters
    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public void setClaims(ArrayList<Claim> claims) {
        super.setClaims(claims);
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

