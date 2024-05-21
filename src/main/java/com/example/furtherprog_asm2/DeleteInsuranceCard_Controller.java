package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
import javafx.event.ActionEvent;
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

public class DeleteInsuranceCard_Controller {
    @FXML
    private ImageView homeIcon;
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
                    cardNumber.clear(); // Clear the input field after delete
                }
            }
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
