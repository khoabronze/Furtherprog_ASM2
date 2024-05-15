package com.example.furtherprog_asm2.Controller;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.time.format.DateTimeFormatter;

public class CreateInsuranceCard_Controller {
    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardHolder;

    @FXML
    private TextField policyOwner;

    @FXML
    private DatePicker expirationDate;

    @FXML
    private Button submit;

    @FXML
    public void submit(ActionEvent event) {
        try {
            String cardNumberData = cardNumber.getText();
            String cardHolderData = cardHolder.getText();
            String policyOwnerData = policyOwner.getText();
            String expirationDateData = expirationDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (cardNumberData.length() != 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Input");
                alert.setContentText("Card Number must be exactly 10 digits long.");
                alert.showAndWait();
                return;
            }

            InsuranceCard insuranceCard = new InsuranceCard(cardNumberData, cardHolderData, policyOwnerData, expirationDateData);
            InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
            boolean success = insuranceCardDao.add(insuranceCard);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if (success) {
                alert.setTitle("Success");
                alert.setContentText("Insurance Card created successfully!");

                // Clear the input in the textfield
                cardNumber.clear();
                cardHolder.clear();
                policyOwner.clear();
                expirationDate.setValue(null);
            } else {
                alert.setTitle("Failure");
                alert.setContentText("Failed to create Insurance Card. Please try again.");
            }
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
