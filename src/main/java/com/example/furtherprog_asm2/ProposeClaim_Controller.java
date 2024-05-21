package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
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

    @FXML
    public void navigateUserProfile() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("User-Profile.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) profileIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
}
