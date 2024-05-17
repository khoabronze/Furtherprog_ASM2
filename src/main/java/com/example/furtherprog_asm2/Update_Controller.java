// Update_Controller.java
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

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

    private InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
    private InsuranceCard originalInsuranceCard;

    public void initializeData(String cardNumberData) {
        originalInsuranceCard = insuranceCardDao.get(cardNumberData).get();
        cardNumber.setText(originalInsuranceCard.getCardNumber());
        cardHolder.setText(originalInsuranceCard.getCardHolder());
        policyOwner.setText(originalInsuranceCard.getPolicyOwner());
        LocalDate date = LocalDate.parse(originalInsuranceCard.getExpirationDate());
        expirationDate.setValue(date);
        cardNumber.setEditable(false);
    }
    @FXML
    public void update(ActionEvent event) throws IOException {
        String newCardHolder = cardHolder.getText();
        String newPolicyOwner = policyOwner.getText();
        LocalDate newExpirationDate = expirationDate.getValue();

        if (newCardHolder.isEmpty()) {
            newCardHolder = originalInsuranceCard.getCardHolder();
        }

        if (newPolicyOwner.isEmpty()) {
            newPolicyOwner = originalInsuranceCard.getPolicyOwner();
        }

        if (newExpirationDate == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            newExpirationDate = LocalDate.parse(originalInsuranceCard.getExpirationDate(), formatter);
        }

        boolean updateSuccessful = insuranceCardDao.update(originalInsuranceCard.getCardNumber(), newCardHolder, newPolicyOwner, newExpirationDate.toString());

        if (updateSuccessful) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update-NavBar.fxml"));
            Parent newSceneParent = loader.load();
            Scene newScene = new Scene(newSceneParent);
            Stage currentStage = (Stage) cardNumber.getScene().getWindow();
            currentStage.setScene(newScene);

            // Add alert for successful update
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Update successful.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Update failed.");
            alert.showAndWait();
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