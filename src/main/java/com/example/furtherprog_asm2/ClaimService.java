package com.example.furtherprog_asm2;

public class ClaimService {
    private DAO<Claim> claimDAO;

    public ClaimService(DAO<Claim> claimDAO) {
        this.claimDAO = claimDAO;
    }

    public void submitClaim(Claim claim) throws IllegalArgumentException {

        claimDAO.save(claim);

    }
    public void DeleteClaim(Claim claim) throws IllegalArgumentException {

        claimDAO.delete(claim);

    }

    public Claim getClaim(String id) {
        // Use the DAO to get the claim from the database
        return claimDAO.get(id);
}}