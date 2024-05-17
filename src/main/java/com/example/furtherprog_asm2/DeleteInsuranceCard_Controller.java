package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

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
                    cardNumber.clear(); // Clear the input field after delete
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    go to homepage
    @FXML
    private HBox homeHBox;
    @FXML
    private void handleHomeNavigation(MouseEvent event) {
        navigateToHomePage();
    }

    @FXML
    private void navigateToHomePage() {
        try {
            Stage stage = (Stage) homeHBox.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/furtherprog_asm2/Homepage-Admin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    go to user page
    @FXML
    private HBox userIcon;

    @FXML
    private void handleUserIconClick(MouseEvent event) {
        navigateToUserProfile();
    }
    @FXML
    private void navigateToUserProfile() {
        try {
            Stage stage = (Stage) userIcon.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/furtherprog_asm2/UserProfile.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
