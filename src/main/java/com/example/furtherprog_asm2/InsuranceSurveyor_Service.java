/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public class InsuranceSurveyor_Service {
    private InsuranceSurveyorDAO<InsuranceSurveyor> insuranceSurveyorDAO;

    public InsuranceSurveyor_Service(InsuranceSurveyorDAO<InsuranceSurveyor> insuranceSurveyorDAO) {
        this.insuranceSurveyorDAO = insuranceSurveyorDAO;
    }

    public List<InsuranceSurveyor> getAllInsuranceSurveyors() {
        return insuranceSurveyorDAO.getAll();
    }

    public Optional<InsuranceSurveyor> getInsuranceSurveyor(String id) {
        return insuranceSurveyorDAO.get(id);
    }

    public InsuranceSurveyor getOneInsuranceSurveyor(String id) {
        return insuranceSurveyorDAO.getOne(id);
    }

    public boolean addInsuranceSurveyor(InsuranceSurveyor insuranceSurveyor) {
        return insuranceSurveyorDAO.add(insuranceSurveyor);
    }

    public boolean updateInsuranceSurveyor(InsuranceSurveyor insuranceSurveyor) {
        return insuranceSurveyorDAO.update(insuranceSurveyor);
    }

    public boolean deleteInsuranceSurveyor(InsuranceSurveyor insuranceSurveyor) {
        return insuranceSurveyorDAO.delete(insuranceSurveyor);
    }
}