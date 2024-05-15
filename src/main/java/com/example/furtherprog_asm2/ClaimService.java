package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public class ClaimService {
    private DAO<Claim> claimDAO;

    public ClaimService(DAO<Claim> claimDAO) {
        this.claimDAO = claimDAO;
    }

    public void submitClaim(Claim claim) throws IllegalArgumentException {
        claimDAO.save(claim);
    }

    public void deleteClaim(Claim claim) throws IllegalArgumentException {
        claimDAO.delete(claim);
    }

    public List<Claim> getAllClaims() {
        return claimDAO.getAll();
    }

    public boolean update(Claim claim) {
        return claimDAO.update(claim);
    }

    public Optional<Claim> getClaim(String id) {
        Claim claim = claimDAO.get(id);
        if (claim != null) {
            return Optional.of(claim);
        } else {
            return Optional.empty();
        }
    }
}