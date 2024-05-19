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

public class ProposeClaim_Controller {
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
    @FXML
    private Button requestForm;
    private ClaimService claimService;
    private String claimIdData;

    public ProposeClaim_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName(); // store the name of the file
        }
    }

    public void initializeData(String claimIdData) {
        this.claimIdData = claimIdData;
        Optional<Claim> optionalClaim = claimService.getClaim(claimIdData);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            Claim_ID_form.setText(claim.getId());
            Claim_Date_form.setValue(convertToLocalDateViaSqlDate((java.sql.Date) claim.getClaimDate()));
            Card_number_form.setText(claim.getCardNumber());
            Claim_exam_date_form.setValue(convertToLocalDateViaSqlDate((java.sql.Date) claim.getExamDate()));
            Claim_IP_form.setText(claim.getInsuredPerson());
            Claim_amount_form.setText(String.valueOf(claim.getClaimAmount()));
            Bank_form.setText(claim.getReiveBankingInfo().getBank());
            Bank_name_form.setText(claim.getReiveBankingInfo().getName());
            Bank_number_form.setText(claim.getReiveBankingInfo().getNumber());
            CLaim_status_form.setValue(claim.getStatus());
        }

        // Make the fields uneditable
        Claim_ID_form.setEditable(false);
        Claim_Date_form.setDisable(true);
        Card_number_form.setEditable(false);
        Claim_exam_date_form.setDisable(true);
        Claim_IP_form.setEditable(false);
        Claim_amount_form.setEditable(false);
        Bank_form.setEditable(false);
        Bank_name_form.setEditable(false);
        Bank_number_form.setEditable(false);
        CLaim_status_form.setDisable(true); // ChoiceBox does not have setEditable method, use setDisable instead
    }

    private LocalDate convertToLocalDateViaSqlDate(java.sql.Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }

    @FXML
    public void handleRequestForm() throws IOException {
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Request-Form.fxml"));

        Scene newScene = new Scene(newSceneParent);

        Stage currentStage = (Stage) requestForm.getScene().getWindow();

        currentStage.setScene(newScene);
    }
}
