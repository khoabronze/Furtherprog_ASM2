package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public class PolicyHolder_Service {
    private final PolicyHolderDAO policyHolderDAO;

    public PolicyHolder_Service(PolicyHolderDAO policyHolderDAO) {
        this.policyHolderDAO = policyHolderDAO;
    }

    public List<PolicyHolder> getAllPolicyHolders() {
        return policyHolderDAO.getAll();
    }

    public Optional<PolicyHolder> getPolicyHolder(String id) {
        return policyHolderDAO.get(id);
    }

    public boolean addPolicyHolder(PolicyHolder policyHolder) {
        return policyHolderDAO.add(policyHolder);
    }

    public boolean updatePolicyHolder(PolicyHolder policyHolder) {
        return policyHolderDAO.update(policyHolder);
    }

    public boolean deletePolicyHolder(PolicyHolder policyHolder) {
        return policyHolderDAO.delete(policyHolder);
    }
}