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

public class UpdateClaimController {
    private Claim originalClaim;
    private ClaimDAO claimDao;
    private ClaimService claimService;

    private String documentName;
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
    private TextField Claim_ID_Box;
    @FXML
    private Button search_button;

    private String claimIdData;

    public UpdateClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimDao = new ClaimDAO(connection);
        this.claimService = new ClaimService(claimDao);
    }

    public void initializeData(String claimIdData) {
            Optional<Claim> claimOptional = claimService.getClaim(claimIdData);
            if (claimOptional.isPresent()) {
                originalClaim = claimOptional.get();
                Claim originalClaim = claimService.getClaim(claimIdData).get();

                Claim_ID_form.setText(originalClaim.getId());

                LocalDate claimDate = LocalDate.parse(originalClaim.getClaimDate().toString());
                Claim_Date_form.setValue(claimDate);

                Card_number_form.setText(originalClaim.getCardNumber());

                LocalDate examDate = LocalDate.parse(originalClaim.getExamDate().toString());
                Claim_exam_date_form.setValue(examDate);

                Claim_IP_form.setText(originalClaim.getInsuredPerson());

                Claim_amount_form.setText(String.valueOf(originalClaim.getClaimAmount()));

                Bank_form.setText(originalClaim.getReiveBankingInfo().getBank());
                Bank_name_form.setText(originalClaim.getReiveBankingInfo().getName());
                Bank_number_form.setText(originalClaim.getReiveBankingInfo().getNumber());

                CLaim_status_form.getItems().setAll(ClaimStatus.values());
                CLaim_status_form.setValue(originalClaim.getStatus());
                Claim_ID_form.setEditable(false);
            } else {
                // Handle the case where the claim is not found
                System.out.println("Claim not found with id: " + claimIdData);
            }
        }




    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName();
        }
    }
    @FXML
    public void update(ActionEvent event) throws IOException {
        String newInsuredPerson = Claim_IP_form.getText();
        String newCardNumber = Card_number_form.getText();
        LocalDate newClaimDate = Claim_Date_form.getValue();
        LocalDate newExamDate = Claim_exam_date_form.getValue();
        double newClaimAmount = Double.parseDouble(Claim_amount_form.getText());
        ClaimStatus newStatus = CLaim_status_form.getValue();
        String newBank = Bank_form.getText();
        String newBankName = Bank_name_form.getText();
        String NewBankNumber = Bank_number_form.getText();
        String newDocuments = documentName;

        if (newInsuredPerson.isEmpty()) {
            newInsuredPerson = originalClaim.getInsuredPerson();
        }

        if (newCardNumber.isEmpty()) {
            newCardNumber = originalClaim.getCardNumber();
        }

        if (newClaimDate == null) {
            newClaimDate = originalClaim.getClaimDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (newExamDate == null) {
            newExamDate = originalClaim.getExamDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        if (newDocuments == null || newDocuments.isEmpty()) {
            newDocuments = String.join(",", originalClaim.getDocuments());
        }
        if (newClaimAmount == 0) {
            newClaimAmount = originalClaim.getClaimAmount();
        }

        if (newStatus == null) {
            newStatus = originalClaim.getStatus();
        }

        if (newBank.isEmpty()) {
            newBank = originalClaim.getReiveBankingInfo().getBank();
        }

        if (newBankName.isEmpty()) {
            newBankName = originalClaim.getReiveBankingInfo().getName();
        }
        if (NewBankNumber.isEmpty()) {
            NewBankNumber = originalClaim.getReiveBankingInfo().getNumber();
        }
        if (newDocuments.isEmpty()) {
            newDocuments = String.join(",", originalClaim.getDocuments());
        }

        BankingInfo newBankInfo = new BankingInfo(newBank, newBankName, NewBankNumber);
        Claim updatedClaim = new Claim();
        updatedClaim.setId(originalClaim.getId());
        updatedClaim.setInsuredPerson(newInsuredPerson);
        updatedClaim.setCardNumber(newCardNumber);
        updatedClaim.setClaimDate(Date.from(newClaimDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        updatedClaim.setExamDate(Date.from(newExamDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        updatedClaim.setClaimAmount(newClaimAmount);
        updatedClaim.setStatus(newStatus);
        updatedClaim.setReiveBankingInfo(newBankInfo);
        updatedClaim.setDocuments(newDocuments);

        boolean updateSuccessful = claimDao.update(updatedClaim);
        if (updateSuccessful) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update-Claim-Search.fxml"));
            Parent newSceneParent = loader.load();
            Scene newScene = new Scene(newSceneParent);
            Stage currentStage = (Stage) Claim_ID_form.getScene().getWindow();
            currentStage.setScene(newScene);

            showAlert("Success", null, "Update successful.");
        } else {
            showAlert("Error", null, "Update failed.");
        }
    }

    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}