package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public interface InsuranceSurveyorDAO <InsuranceSurveyor>{
    List<InsuranceSurveyor> getAll();
    Optional<InsuranceSurveyor> get(String id);
    InsuranceSurveyor getOne(String id);
    boolean add(InsuranceSurveyor is);
    boolean update(InsuranceSurveyor is);
    boolean delete(InsuranceSurveyor is);
}
