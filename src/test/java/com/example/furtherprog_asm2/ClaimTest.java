package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimTest {

    private Claim claim;

    @BeforeEach
    public void setUp() {
        claim = new Claim(1, 1, 1, 1000.0, LocalDate.of(2023, 5, 18), "Medical");
    }

    @Test
    public void testGetClaimId() {
        assertEquals(1, claim.getClaimId());
    }

    @Test
    public void testSetClaimId() {
        claim.setClaimId(2);
        assertEquals(2, claim.getClaimId());
    }

    @Test
    public void testGetPolicyHolderId() {
        assertEquals(1, claim.getPolicyHolderId());
    }

    @Test
    public void testSetPolicyHolderId() {
        claim.setPolicyHolderId(2);
        assertEquals(2, claim.getPolicyHolderId());
    }

    @Test
    public void testGetDependentId() {
        assertEquals(1, claim.getDependentId());
    }

    @Test
    public void testSetDependentId() {
        claim.setDependentId(2);
        assertEquals(2, claim.getDependentId());
    }

    @Test
    public void testGetAmount() {
        assertEquals(1000.0, claim.getAmount());
    }

    @Test
    public void testSetAmount() {
        claim.setAmount(2000.0);
        assertEquals(2000.0, claim.getAmount());
    }

    @Test
    public void testGetDateOfClaim() {
        assertEquals(LocalDate.of(2023, 5, 18), claim.getDateOfClaim());
    }

    @Test
    public void testSetDateOfClaim() {
        claim.setDateOfClaim(LocalDate.of(2023, 6, 18));
        assertEquals(LocalDate.of(2023, 6, 18), claim.getDateOfClaim());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Medical", claim.getDescription());
    }

    @Test
    public void testSetDescription() {
        claim.setDescription("Dental");
        assertEquals("Dental", claim.getDescription());
    }
}
