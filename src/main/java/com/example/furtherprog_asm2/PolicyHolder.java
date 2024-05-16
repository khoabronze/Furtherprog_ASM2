package com.example.furtherprog_asm2;

import java.util.ArrayList;
import java.util.HashMap;

class PolicyHolder extends User {
    private HashMap<String, Dependent> dependentList;

    public PolicyHolder() {
        super();
        this.dependentList = new HashMap<>();
    }

    public PolicyHolder(HashMap<String, Dependent> dependentList) {
        this.dependentList = dependentList;
    }

    public PolicyHolder(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, ArrayList<Claim> claims, String role, HashMap<String, Dependent> dependentList) {
        super(id, name, phone, email, address, password, insuranceCard, claims, role);
        this.dependentList = dependentList;
    }

    protected HashMap<String, Dependent> getDependentList() {
        return dependentList;
    }

    protected void setDependentList(HashMap<String, Dependent> dependentList) {
        this.dependentList = dependentList;
    }
}