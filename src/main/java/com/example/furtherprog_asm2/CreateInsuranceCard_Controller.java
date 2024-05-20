package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CreateInsuranceCard_Controller {
    @FXML
    private ImageView homeIcon;
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
