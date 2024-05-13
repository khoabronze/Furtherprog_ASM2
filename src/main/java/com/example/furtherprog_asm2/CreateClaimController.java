
package com.example.furtherprog_asm2;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
        this.ClaimService = new ClaimService(new ClaimDAO());
    }
    @FXML
    public void uploadDocument() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName(); // store the name of the file
        }
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
        Claim claim = new Claim();
        claim.setId(Claim_ID_form.getText());

        LocalDate localDate = Claim_Date_form.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        claim.setClaimDate(date);

        claim.setCardNumber(Card_number_form.getText());

        LocalDate localExamDate = Claim_exam_date_form.getValue();
        Date examDate = Date.from(localExamDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        claim.setExamDate(examDate);        claim.setInsuredPerson(Claim_IP_form.getText());

        double claimAmount = Double.parseDouble(Claim_amount_form.getText());
        claim.setClaimAmount(claimAmount);

        claim.setStatus(CLaim_status_form.getValue());
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank(Bank_form.getText());
        bankingInfo.setName(Bank_name_form.getText());
        bankingInfo.setNumber(Bank_number_form.getText());
        claim.setReiveBankingInfo(bankingInfo);

        if (documentName != null) {
            claim.getDocuments().add(documentName);
        }
        ClaimService.createClaim(claim);
    }

}
