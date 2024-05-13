package com.example.furtherprog_asm2;

public class ClaimService {
    private DAO<Claim> claimDAO;

    public ClaimService(DAO<Claim> claimDAO) {
        this.claimDAO = claimDAO;
    }

    public void submitClaim(Claim claim) throws IllegalArgumentException {

               claimDAO.save(claim);

}}