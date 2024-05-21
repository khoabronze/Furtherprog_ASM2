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

public class DeleteInsuranceManager_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField idInput;
    @FXML
    private Button deleteButton;
    private Db_function dbFunction = new Db_function();

    @FXML
    private void handleDeleteInsuranceManager() {
        String id = idInput.getText();

        try {
            if (id.isEmpty()) {
                showAlert("Error Dialog", "Input Error", "ID field is required");
                return;
            }

            InsuranceManager_DAO insuranceManagerDAO = new InsuranceManager_DAO(dbFunction.connect_to_db());
            InsuranceManager insuranceManager = insuranceManagerDAO.getOne(id);

            if (insuranceManager == null) {
                showAlert("Error Dialog", "Delete Result", "No Insurance Surveyor found with the given ID");
            } else {
                boolean success = insuranceManagerDAO.delete(insuranceManager);

                if (success) {
                    showAlert("Information Dialog", "Delete Result", "Insurance Surveyor successfully deleted");
                } else {
                    showAlert("Error Dialog", "Delete Result", "Failed to delete Insurance Surveyor");
                }
            }
        } finally {
            idInput.clear();
        }
    }
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
            case "PolicyHolder":
                homepageFile = "Homepage-PolicyHolder.fxml";
                break;
            case "PolicyOwner":
                homepageFile = "Homepage-PolicyOwner.fxml";
                break;
            case "InsuranceManager":
                homepageFile = "Homepage-InsuranceManager.fxml";
                break;
            case "InsuranceSurveyor":
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
