package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BankingInfoTest {

    private BankingInfo bankingInfo;

    @BeforeEach
    public void setUp() {
        bankingInfo = new BankingInfo(1, "John Doe", "1234567890", "Bank of Somewhere", "BS123456789");
    }

    @Test
    public void testGetBankingInfoId() {
        assertEquals(1, bankingInfo.getBankingInfoId());
    }

    @Test
    public void testSetBankingInfoId() {
        bankingInfo.setBankingInfoId(2);
        assertEquals(2, bankingInfo.getBankingInfoId());
    }

    @Test
    public void testGetAccountHolderName() {
        assertEquals("John Doe", bankingInfo.getAccountHolderName());
    }

    @Test
    public void testSetAccountHolderName() {
        bankingInfo.setAccountHolderName("Jane Doe");
        assertEquals("Jane Doe", bankingInfo.getAccountHolderName());
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals("1234567890", bankingInfo.getAccountNumber());
    }

    @Test
    public void testSetAccountNumber() {
        bankingInfo.setAccountNumber("0987654321");
        assertEquals("0987654321", bankingInfo.getAccountNumber());
    }

    @Test
    public void testGetBankName() {
        assertEquals("Bank of Somewhere", bankingInfo.getBankName());
    }

    @Test
    public void testSetBankName() {
        bankingInfo.setBankName("New Bank");
        assertEquals("New Bank", bankingInfo.getBankName());
    }

    @Test
    public void testGetBankBranchCode() {
        assertEquals("BS123456789", bankingInfo.getBankBranchCode());
    }

    @Test
    public void testSetBankBranchCode() {
        bankingInfo.setBankBranchCode("NB987654321");
        assertEquals("NB987654321", bankingInfo.getBankBranchCode());
    }
}
