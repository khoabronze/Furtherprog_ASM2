package com.example.furtherprog_asm2;

public class ClaimService {
    private DAO<Claim> claimDAO;

    public ClaimService(DAO<Claim> claimDAO) {
        this.claimDAO = claimDAO;
    }

    public void createClaim(Claim claim) {
        claimDAO.save(claim);
    }

    // Other methods for reading, updating, and deleting claims...
}