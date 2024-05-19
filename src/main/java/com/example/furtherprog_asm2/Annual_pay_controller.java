package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class Annual_pay_controller {
    @FXML
    private TextField Annual_pay;

    private DependentDAO<Dependent> dependentDAO;

    public Annual_pay_controller() {
        Db_function dbFunction = new Db_function();
        Connection conn = dbFunction.connect_to_db();
        this.dependentDAO = new DependentDAO_IMP(conn);
    }

    public TextField getAnnual_pay() {
        return Annual_pay;
    }

    public void setAnnual_pay(TextField annual_pay) {
        Annual_pay = annual_pay;
    }

    public DependentDAO<Dependent> getDependentDAO() {
        return dependentDAO;
    }

    public void setDependentDAO(DependentDAO<Dependent> dependentDAO) {
        this.dependentDAO = dependentDAO;
    }

    @FXML
    public void Calculate() {
        // Create a PolicyOwner
        PolicyOwner policyOwner = new PolicyOwner();

        // Get all dependents from the database
        List<Dependent> dependents = dependentDAO.getAll();

        // Create a HashMap to store the dependents
        HashMap<String, Dependent> dependentList = new HashMap<>();

        // Add each dependent to the HashMap
        for (Dependent dependent : dependents) {
            dependentList.put(dependent.getId(), dependent);
        }

        // Set the dependentList of the policyOwner
        policyOwner.setDependentList(dependentList);
        double annual_pay = policyOwner.calculateYearlyPayment();

        // Calculate the annual pay
        double roundedAnnualPay = Math.round(annual_pay * 100.0) / 100.0;

        // Display the annual pay in the TextField
        Annual_pay.setText(String.valueOf(roundedAnnualPay));
    }
}