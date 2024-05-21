package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public class PolicyOwnerService {
    private PolicyOwnerDao_IMP policyOwnerDAO;

    public PolicyOwnerService(PolicyOwnerDao_IMP policyOwnerDAO) {
        this.policyOwnerDAO = policyOwnerDAO;
    }

    public boolean addPolicyOwner(PolicyOwner policyOwner) {
        return policyOwnerDAO.add(policyOwner);
    }

    public Optional<PolicyOwner> getPolicyOwner(String id) {
        return policyOwnerDAO.get(id);
    }

    public boolean updatePolicyOwner(PolicyOwner policyOwner) {
        return policyOwnerDAO.update(policyOwner);
    }

    public boolean deletePolicyOwner(PolicyOwner policyOwner) {
        return policyOwnerDAO.delete(policyOwner);
    }

    public List<PolicyOwner> getAllPolicyOwners() {
        return policyOwnerDAO.getAll();
    }
}