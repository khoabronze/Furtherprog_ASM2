package com.example.furtherprog_asm2;

import java.util.ArrayList;

public class InsuranceManager extends User {
    // Default Constructor
    public InsuranceManager() {
        super();
        this.setRole("Insurance Manager");
    }

    // Constructor
    public InsuranceManager(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, ArrayList<Claim> claims) {
        super(id, name, phone, email, address, password, insuranceCard, claims,"Insurance Manager");
    }
}
