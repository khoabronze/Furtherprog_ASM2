/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class Switch_Status_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    @FXML
    private TextField Claim_ID_form;

    @FXML
    private ChoiceBox<ClaimStatus> CLaim_status_form;

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

    private ClaimService claimService;
    private String documentName;

    public Switch_Status_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void initializeData(String claimId) {
        Optional<Claim> optionalClaim = claimService.getClaim(claimId);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();

            // Set the values of the form fields to the values of the claim
            Claim_ID_form.setText(claim.getId());
            Claim_ID_form.setEditable(false);

            populateStatusChoiceBox(); // Populate the ChoiceBox with status options
            CLaim_status_form.setValue(claim.getStatus());
            CLaim_status_form.setDisable(false); // Enable the ChoiceBox

            // Convert java.sql.Date to java.time.LocalDate
            Claim_Date_form.setValue(convertToLocalDateViaSqlDate((java.sql.Date) claim.getClaimDate()));
            Claim_Date_form.setDisable(true);

            Card_number_form.setText(claim.getCardNumber());
            Card_number_form.setEditable(false);

            Claim_exam_date_form.setValue(convertToLocalDateViaSqlDate((java.sql.Date) claim.getExamDate()));
            Claim_exam_date_form.setDisable(true);

            Claim_IP_form.setText(claim.getInsuredPerson());
            Claim_IP_form.setEditable(false);

            Claim_amount_form.setText(String.valueOf(claim.getClaimAmount()));
            Claim_amount_form.setEditable(false);

            Bank_form.setText(claim.getReiveBankingInfo().getBank());
            Bank_form.setEditable(false);

            Bank_name_form.setText(claim.getReiveBankingInfo().getName());
            Bank_name_form.setEditable(false);

            Bank_number_form.setText(claim.getReiveBankingInfo().getNumber());
            Bank_number_form.setEditable(false);
        }
    }

    private void populateStatusChoiceBox() {
        CLaim_status_form.setItems(FXCollections.observableArrayList(ClaimStatus.values()));
    }

    // Method to convert java.util.Date to java.time.LocalDate
    public LocalDate convertToLocalDateViaSqlDate(java.sql.Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }

    @FXML
    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName(); // store the name of the file
        }
    }

    @FXML
    public void updateStatus() throws IOException {
        String claimIdData = Claim_ID_form.getText();
        ClaimStatus claimStatusData = CLaim_status_form.getValue();

        if (claimStatusData == null) {
            showAlert("Error", null, "Please select a status.");
            return;
        }

        // Fetch the current claim from the database
        Optional<Claim> optionalClaim = claimService.getClaim(claimIdData);
        if (!optionalClaim.isPresent()) {
            showAlert("Error", null, "No claim found with the provided claim id.");
            return;
        }
        Claim currentClaim = optionalClaim.get();

        // Update the status of the claim in the database
        currentClaim.setStatus(claimStatusData);
        boolean success = claimService.update(currentClaim);

        // Update the status of the claim in the form
        CLaim_status_form.setValue(claimStatusData);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if (success) {
            alert.setTitle("Success");
            alert.setContentText("Claim status updated successfully!");

            // Load the Switch-Status-NavBar.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Switch-Status-NavBar.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the scene to the loaded scene
            Stage stage = (Stage) CLaim_status_form.getScene().getWindow();
            stage.setScene(new Scene(root));
        } else {
            alert.setTitle("Failure");
            alert.setContentText("Failed to update Claim status. Please try again.");
        }
        alert.showAndWait();
    }

    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
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