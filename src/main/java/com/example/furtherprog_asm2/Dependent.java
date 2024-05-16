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
}

