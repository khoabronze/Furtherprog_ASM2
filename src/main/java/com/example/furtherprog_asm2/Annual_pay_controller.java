/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class Annual_pay_controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField Annual_pay;
    @FXML
    private ImageView profileIcon;

    private DependentDAO<Dependent> dependentDAO;

    public Annual_pay_controller() {
        Db_function dbFunction = new Db_function();
        Connection conn = dbFunction.connect_to_db();
        this.dependentDAO = new DependentDAO_IMP(conn);
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

    @FXML
    public void handleHomeIconClick() {
        String role = Login_Controller.loggedInRole;
        String homepageFile = null;

        switch (role) {
            case "Admin":
                homepageFile = "Homepage-Admin.fxml";
                break;
            case "Dependent":
                homepageFile = "Homepage-Dependent.fxml";
                break;
            case "Policy Holder":
                homepageFile = "Homepage-PolicyHolder.fxml";
                break;
            case "Policy Owner":
                homepageFile = "Homepage-PolicyOwner.fxml";
                break;
            case "Insurance Manager":
                homepageFile = "Homepage-InsuranceManager.fxml";
                break;
            case "Insurance Surveyor":
                homepageFile = "Homepage-InsuranceSurveyor.fxml";
                break;
            default:
                // handle unknown role
                break;
        }

        if (homepageFile != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(homepageFile));
                Stage stage = (Stage) homeIcon.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void navigateUserProfile() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("User-Profile.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) profileIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
}