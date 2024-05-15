// Update_Controller.java
package com.example.furtherprog_asm2.Controller;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class Update_Controller {
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField cardHolder;
    @FXML
    private TextField policyOwner;
    @FXML
    private DatePicker expirationDate;
    @FXML
    private Button update;

    public void initializeData(String cardNumberData) {
        cardNumber.setText(cardNumberData);
        cardNumber.setEditable(false);
    }
    @FXML
    public void update(ActionEvent event) {
        try {
            String cardNumberData = cardNumber.getText();
            String cardHolderData = cardHolder.getText();
            String policyOwnerData = policyOwner.getText();
            String expirationDateData = expirationDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            InsuranceCard insuranceCard = new InsuranceCard(cardNumberData, cardHolderData, policyOwnerData, expirationDateData);
            InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
            boolean success = insuranceCardDao.update(insuranceCard);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if (success) {
                alert.setTitle("Success");
                alert.setContentText("Insurance Card updated successfully!");

                // Clear the input in the textfield
                cardNumber.clear();
                cardHolder.clear();
                policyOwner.clear();
                expirationDate.setValue(null);
            } else {
                alert.setTitle("Failure");
                alert.setContentText("Failed to update Insurance Card. Please try again.");
            }
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}