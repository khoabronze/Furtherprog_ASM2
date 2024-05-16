
package com.example.furtherprog_asm2;
import com.example.furtherprog_asm2.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class CreateClaimController {
    private String documentName; // new field to hold the name of the uploaded document
    private ClaimService ClaimService;
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

    @FXML
    public void initialize() {
        CLaim_status_form.getItems().setAll(ClaimStatus.values());
    }
    public CreateClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.ClaimService = new ClaimService(new ClaimDAO(connection));
    }

    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName(); // store the name of the file
        }
    }
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
    public void submit() {
        try {
            // Validate the claim id
            String id = Claim_ID_form.getText();
            if (!id.matches("\\d{10}")) {
                showAlert("Error Dialog", "Input Error", "Invalid claim id. Must be 10 numbers.");
                return;
            }

            // Validate the claim date
            LocalDate localDate = Optional.ofNullable(Claim_Date_form.getValue())
                    .orElseThrow(() -> new IllegalArgumentException("Claim date is required"));
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Validate the card number
            String cardNumber = Card_number_form.getText();
            if (cardNumber.isEmpty()) {
                showAlert("Error Dialog", "Input Error", "Card number is required");
                return;
            }

            // Validate the exam date
            LocalDate localExamDate = Optional.ofNullable(Claim_exam_date_form.getValue())
                    .orElseThrow(() -> new IllegalArgumentException("Exam date is required"));
            Date examDate = Date.from(localExamDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Validate the insured person
            String insuredPerson = Claim_IP_form.getText();
            if (insuredPerson.isEmpty()) {
                showAlert("Error Dialog", "Input Error", "Insured person is required");
                return;
            }

            // Validate the claim amount
            String claimAmountStr = Claim_amount_form.getText();
            double claimAmount = Double.parseDouble(claimAmountStr);
            if (claimAmount < 0) {
                showAlert("Error Dialog", "Input Error", "Claim amount cannot be negative");
                return;
            }

            // Validate the claim status
            ClaimStatus status = CLaim_status_form.getValue();
            if (status == null) {
                showAlert("Error Dialog", "Input Error", "Claim status is required");
                return;
            }

            String Bank = Bank_form.getText();
            if (Bank.isEmpty()) {
                showAlert("Error Dialog", "Input Error", "Bank is required");
                return;
            }

            String bankName = Bank_name_form.getText();
            if (bankName.isEmpty()) {
                showAlert("Error Dialog", "Input Error", "Name is required");
                return;
            }

            // Validate the bank number
            String bankNumber = Bank_number_form.getText();
            if (bankNumber.isEmpty()) {
                showAlert("Error Dialog", "Input Error", "Bank number is required");
                return;
            }

            // Create a new BankingInfo object and set its properties
            BankingInfo bankingInfo = new BankingInfo();
            bankingInfo.setName(bankName);
            bankingInfo.setNumber(bankNumber);
            // Create a new Claim object and set its properties
            Claim claim = new Claim();
            claim.setId(id);
            claim.setClaimDate(date);
            claim.setCardNumber(cardNumber);
            claim.setExamDate(examDate);
            claim.setReiveBankingInfo(bankingInfo);
            claim.setInsuredPerson(insuredPerson);
            claim.setClaimAmount(claimAmount);
            claim.setDocuments(documentName); // set the document name
            claim.setStatus(status);

            // Submit the claim
            ClaimService.submitClaim(claim);
        } catch (NumberFormatException e) {
            showAlert("Error Dialog", "Input Error", "Invalid number format");
        } catch (IllegalArgumentException e) {
            showAlert("Error Dialog", "Input Error", e.getMessage());
        }
    }
}
