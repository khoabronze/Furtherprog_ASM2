package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation_Controller {
    // Main buttons and logos
    @FXML
    private ImageView viewInsuranceCardIcon;
    @FXML
    private ImageView viewPolicyOwnerIcon;
    @FXML
    private ImageView viewPolicyHolderIcon;
    @FXML
    private ImageView viewDependentIcon;
    @FXML
    private ImageView viewInsuranceSurveyorIcon;
    @FXML
    private ImageView viewInsuranceManagerIcon;

    // Insurance Surveyor buttons and logos
    @FXML
    private ImageView claimInsuranceSurveyorIcon;
    @FXML
    private ImageView insurancecardInsuranceSurveyorIcon;
    @FXML
    private ImageView requestInsuranceSurveyorIcon;
    @FXML
    private ImageView customersInsuranceSurveyorIcon;

    //
    @FXML
    private ImageView claimViewButton;
    @FXML
    private ImageView switchStatusButton;
    @FXML
    private ImageView requestViewButton;
    @FXML
    private ImageView approveDeclineRequestButton;

    // Navigation between pages (Main)
    @FXML
    private void navigateTableViewDisplayInsuranceCard() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("TableView-Display-InsuranceCard.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewInsuranceCardIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    private void navigateViewPO() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("View_PO.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewPolicyOwnerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    private void navigateViewPH() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("View_PH.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewPolicyHolderIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    private void navigateGetAllDependent() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Get-all-dependent.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewDependentIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateTableViewDisplayInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("TableView-Display-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateTableViewDisplayInsuranceManager() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("TableView-Display-InsuranceManager.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewInsuranceManagerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    // Navigation between pages (Insurance Surveyor)
    @FXML
    private void navigateClaimMenuInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Claim-Menu-Insurance-Surveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) claimInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    private void navigateInsuranceCardMenuInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("InsuranceCard-Menu-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) insurancecardInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    private void navigateRequestMenuInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Request-Menu-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) requestInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    private void navigateCustomersMenuInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Customers-Menu-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) customersInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    // -------------------
    @FXML
    private void handleClaimViewNavigation() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Get-All-Claim.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) claimViewButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    @FXML
    private void handleSwitchStatusNavigation() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Switch-Status-NavBar.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) switchStatusButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    @FXML
    private void handleRequestFormNavigation() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Propose-Search-Claim-NavBar.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) switchStatusButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    @FXML
    private void handleRequestViewNavigation() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("TableView-Display-Request.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) requestViewButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    @FXML
    private void handleApproveDeclineRequest() throws IOException{
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Approve-Decline-NavBar.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) approveDeclineRequestButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
}
