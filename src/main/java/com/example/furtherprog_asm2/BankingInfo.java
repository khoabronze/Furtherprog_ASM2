package com.example.furtherprog_asm2;

/**
 * @author <Dong Dang Khoa- s3986281>
 */

public class BankingInfo {
    private String Bank;
    private String Name;
    private String Number;

    public BankingInfo() {
        this.Bank = "Default";
        this.Name = "Default";
        this.Number = "Default";
    }

    public BankingInfo(String bank, String name, String number) {
        this.Bank = bank;
        this.Name = name;
        this.Number = number;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    @Override
    public String toString() {
        return String.format("%s – %s – %s", Bank, Name, Number);
    }


    // for testing
    private int bankingInfoId;
    private String accountHolderName;
    private String accountNumber;
    private String bankName;
    private String bankBranchCode;

    public BankingInfo(int bankingInfoId, String accountHolderName, String accountNumber, String bankName, String bankBranchCode) {
        this.bankingInfoId = bankingInfoId;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.bankBranchCode = bankBranchCode;
    }

    public int getBankingInfoId() {
        return bankingInfoId;
    }

    public void setBankingInfoId(int bankingInfoId) {
        this.bankingInfoId = bankingInfoId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }
}