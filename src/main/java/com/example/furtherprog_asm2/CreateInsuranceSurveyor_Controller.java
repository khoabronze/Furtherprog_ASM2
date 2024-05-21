package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CreateInsuranceSurveyor_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField idBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField addressBox;
    @FXML
    private TextField passwordBox;
    @FXML
    private Button submitButton;
    private Db_function dbFunction = new Db_function();

    @FXML
    public void handleCreateNewInsuranceSurveyor() {
        String id = idBox.getText();
        String name = nameBox.getText();
        String phone = phoneBox.getText();
        String email = emailBox.getText();
        String address = addressBox.getText();
        String password = passwordBox.getText();

        if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
            showAlert("Error Dialog", "Input Error", "All fields are required");
            return;
        }

        // Check if the ID follows the format "is-10 numbers"
        if (!id.matches("is-\\d{10}")) {
            showAlert("Error Dialog", "Input Error", "ID must be in the format 'is-10 numbers'");
            return;
        }

        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId(id);
        insuranceSurveyor.setName(name);
        insuranceSurveyor.setPhone(phone);
        insuranceSurveyor.setEmail(email);
        insuranceSurveyor.setAddress(address);
        insuranceSurveyor.setPassword(password);

        InsuranceSurveyor_DAO insuranceSurveyorDAO = new InsuranceSurveyor_DAO(dbFunction.connect_to_db());
        boolean success = insuranceSurveyorDAO.add(insuranceSurveyor);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operation Status");
        if (success) {
            alert.setHeaderText("Success");
            alert.setContentText("Insurance Surveyor has been added successfully.");

            // Clear all the fields
            idBox.clear();
            nameBox.clear();
            phoneBox.clear();
            emailBox.clear();
            addressBox.clear();
            passwordBox.clear();
        } else {
            alert.setHeaderText("Failed");
            alert.setContentText("Failed to add Insurance Surveyor. Please try again.");
        }
        alert.showAndWait();
    }

    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
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
}
