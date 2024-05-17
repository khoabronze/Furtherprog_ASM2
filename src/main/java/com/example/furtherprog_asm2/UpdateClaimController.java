package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class UpdateClaimController {
    private String documentName; // new field to hold the name of the uploaded document
    @FXML
    private TextField Claim_ID_form;

    @FXML
    private DatePicker Claim_Date_form;

    @FXML
    private TextField Card_number_form;

    @FXML
    private DatePicker Claim_exam_date_form;

    @FXML
    private TextField Claim_IP_form;

    @FXML
    private TextField Claim_amount_form;

    @FXML
    private TextField Bank_form;

    @FXML
    private TextField Bank_name_form;

    @FXML
    private TextField Bank_number_form;

    @FXML
    private ChoiceBox<ClaimStatus> CLaim_status_form;
    private ClaimService claimService;

    @FXML
    private TextField Claim_ID_Box;
    @FXML
    private Button search_button;

    private String claimIdData;

    public UpdateClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void initializeData(String claimIdData) {
        this.claimIdData = claimIdData;
        CLaim_status_form.getItems().setAll(ClaimStatus.values());
        Claim_ID_form.setEditable(false);
    }


    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName(); // store the name of the file
        }
    }
    @FXML
    public void update(ActionEvent event) {
        try {
            String claimIdData = Claim_ID_form.getText();

            // Fetch the current claim from the database
            Optional<Claim> optionalClaim = claimService.getClaim(claimIdData);
            if (!optionalClaim.isPresent()) {
                showAlert("Error", null, "No claim found with the provided claim id.");
                return;
            }
            Claim currentClaim = optionalClaim.get();

            LocalDate claimDateData = Claim_Date_form.getValue();
            Date date = null;
            if (claimDateData != null) {
                LocalDate localDate = claimDateData;
                date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                date = currentClaim.getClaimDate();
            }

            String cardNumberData = Card_number_form.getText();
            if (cardNumberData == null || cardNumberData.isEmpty()) {
                cardNumberData = currentClaim.getCardNumber();
            }

            LocalDate claimExamDateData = Claim_exam_date_form.getValue();
            Date examdate = null;
            if (claimExamDateData != null) {
                LocalDate localExamDate = claimExamDateData;
                examdate = Date.from(localExamDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                examdate = currentClaim.getExamDate();
            }

            String claimIPData = Claim_IP_form.getText();
            if (claimIPData == null || claimIPData.isEmpty()) {
                claimIPData = currentClaim.getInsuredPerson();
            }

            String claimAmountStr = Claim_amount_form.getText();
            Double claimAmount = null;
            if (claimAmountStr != null && !claimAmountStr.isEmpty()) {
                try {
                    claimAmount = Double.parseDouble(claimAmountStr);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid number for Claim Amount.");
                    alert.showAndWait();
                    return; // Exit the method if the input is invalid
                }
            } else {
                claimAmount = currentClaim.getClaimAmount();
            }

            String bankData = Bank_form.getText();
            if (bankData == null || bankData.isEmpty()) {
                bankData = currentClaim.getReiveBankingInfo().getBank();
            }

            String bankNameData = Bank_name_form.getText();
            if (bankNameData == null || bankNameData.isEmpty()) {
                bankNameData = currentClaim.getReiveBankingInfo().getName();
            }

            String bankNumberData = Bank_number_form.getText();
            if (bankNumberData == null || bankNumberData.isEmpty()) {
                bankNumberData = currentClaim.getReiveBankingInfo().getNumber();
            }

            BankingInfo bankingInfo = new BankingInfo();
            bankingInfo.setBank(bankData);
            bankingInfo.setName(bankNameData);
            bankingInfo.setNumber(bankNumberData);

            ClaimStatus claimStatusData = CLaim_status_form.getValue();
            if (claimStatusData == null) {
                claimStatusData = currentClaim.getStatus();
            }

            Claim claim = new Claim(claimIdData, date, claimIPData, cardNumberData, examdate, documentName, claimAmount, claimStatusData, bankingInfo);
            boolean success = claimService.update(claim);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if (success) {
                alert.setTitle("Success");
                alert.setContentText("Claim updated successfully!");

                // Clear the input in the text fields
                Claim_ID_form.clear();
                Claim_Date_form.setValue(null);
                Card_number_form.clear();
                Claim_exam_date_form.setValue(null);
                Claim_IP_form.clear();
                Claim_amount_form.clear();
                Bank_form.clear();
                Bank_name_form.clear();
                Bank_number_form.clear();
                CLaim_status_form.setValue(null);
            } else {
                alert.setTitle("Failure");
                alert.setContentText("Failed to update Claim. Please try again.");
            }
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
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