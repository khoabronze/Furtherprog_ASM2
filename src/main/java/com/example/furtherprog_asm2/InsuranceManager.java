/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import java.util.ArrayList;

public class InsuranceManager extends User {
    // Default Constructor
    public InsuranceManager() {
        super();
        this.setRole("Insurance Manager");
    }

    // Constructor
    public InsuranceManager(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, Request request, ArrayList<Claim> claims) {
        super(id, name, phone, email, address, password, insuranceCard, request, claims,"Insurance Manager");
    }
}
