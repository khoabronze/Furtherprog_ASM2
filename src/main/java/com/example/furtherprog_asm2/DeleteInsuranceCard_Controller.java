package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteInsuranceCard_Controller {
    @FXML
    private TextField cardNumber;

    @FXML
    private Button delete;

    @FXML
    public void delete(ActionEvent event) {
        try {
            String cardNumberData = cardNumber.getText();
            InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
            if (insuranceCardDao.get(cardNumberData).isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Failure");
                alert.setContentText(cardNumberData + " card number does not exist");
                alert.showAndWait();
            } else {
                InsuranceCard insuranceCard = new InsuranceCard(cardNumberData, "", "", "");
                boolean isDeleted = insuranceCardDao.delete(insuranceCard);
                if (isDeleted) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Success");
                    alert.setContentText("Insurance Card deleted successfully!");
                    alert.showAndWait();
                    cardNumber.clear(); // Clear the input field after delete
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Failure");
                    alert.setContentText("Failed to delete the Insurance Card!");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
