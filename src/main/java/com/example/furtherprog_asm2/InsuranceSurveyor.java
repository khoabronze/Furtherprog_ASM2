package com.example.furtherprog_asm2;

import java.util.ArrayList;

public class InsuranceSurveyor extends User {
    // Default Constructor
    public InsuranceSurveyor() {
        super();
        this.setRole("Insurance Surveyor");
    }

    // Constructor
    public InsuranceSurveyor(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, Request request, ArrayList<Claim> claims) {
        super(id, name, phone, email, address, password, insuranceCard, request, claims,"Insurance Surveyor");
    }
}
