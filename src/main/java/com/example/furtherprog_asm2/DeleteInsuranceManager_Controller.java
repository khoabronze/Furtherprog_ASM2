package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteInsuranceManager_Controller {
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
}
