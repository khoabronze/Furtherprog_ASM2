package com.example.furtherprog_asm2;

import java.util.ArrayList;
import java.util.HashMap;

public class PolicyOwner extends User {
    private HashMap<String, Dependent> dependentList;

    public PolicyOwner() {
        super();
        this.dependentList = new HashMap<>();
    }

    public PolicyOwner(HashMap<String, Dependent> dependentList) {
        super();
        this.dependentList = dependentList;
    }

    public PolicyOwner(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, ArrayList<Claim> claims, String role, HashMap<String, Dependent> dependentList) {
        super(id, name, phone, email, address, password, insuranceCard, claims, role);
        this.dependentList = dependentList;
    }

    protected HashMap<String, Dependent> getDependentList() {
        return dependentList;
    }

    protected void setDependentList(HashMap<String, Dependent> dependentList) {
        this.dependentList = dependentList;
    }

    public double calculateYearlyPayment() {
        double policyHolderCost = 300; // Cost per policy holder
        double dependentCost = policyHolderCost * 0.6; // Each dependent costs 60% of the rate

        double totalCost = policyHolderCost; // Start with the cost for the policy holder

        // Add the cost for each dependent
        for (Dependent dependent : dependentList.values()) {
            totalCost += dependentCost;
        }

        return totalCost;
    }}