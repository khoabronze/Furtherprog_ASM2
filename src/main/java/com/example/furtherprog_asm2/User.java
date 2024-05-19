package com.example.furtherprog_asm2;

import java.util.ArrayList;
import java.util.Random;

public abstract class User  {
    private String id;
    private String Name;
    private String Phone;
    private String Email;
    private String Address;
    private String Password;
    private InsuranceCard insuranceCard;
    private ArrayList<Claim> claims;
    private String role; // Added role attribute

    public User(){
        this.id = generateUniqueId();
        this.Name = "default";
        this.insuranceCard = new InsuranceCard();
        this.claims = new ArrayList<>();
        this.Email = "default";
        this.Phone = "default";
        this.Address = "default";
        this.Password = "default";
        this.role = "default";
    }

    public User(String id, String name, String phone, String email, String address, String password, InsuranceCard insuranceCard, ArrayList<Claim> claims, String role) {
        this.id = id;
        Name = name;
        Phone = phone;
        Email = email;
        Address = address;
        Password = password;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }

    private String generateUniqueId() {
        StringBuilder sb = new StringBuilder("c-");
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public ArrayList<Claim> getClaims() {
        return claims;
    }

    public void setClaims(ArrayList<Claim> claims) {
        this.claims = claims;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}