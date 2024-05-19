package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public class InsuranceManager_Service {
    private InsuranceManagerDAO<InsuranceManager> insuranceManagerDAO;

    public InsuranceManager_Service(InsuranceManagerDAO<InsuranceManager> insuranceManagerDAO) {
        this.insuranceManagerDAO = insuranceManagerDAO;
    }

    public List<InsuranceManager> getAllInsuranceManagers() {
        return insuranceManagerDAO.getAll();
    }

    public Optional<InsuranceManager> getInsuranceManager(String id) {
        return insuranceManagerDAO.get(id);
    }

    public InsuranceManager getOneInsuranceManager(String id) {
        return insuranceManagerDAO.getOne(id);
    }

    public boolean addInsuranceManager(InsuranceManager insuranceManager) {
        return insuranceManagerDAO.add(insuranceManager);
    }

    public boolean updateInsuranceManager(InsuranceManager insuranceManager) {
        return insuranceManagerDAO.update(insuranceManager);
    }

    public boolean deleteInsuranceManager(InsuranceManager insuranceManager) {
        return insuranceManagerDAO.delete(insuranceManager);
    }
}