package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation_Controller {
    // Insurance Surveyor buttons and logos
    @FXML
    private ImageView claimInsuranceSurveyorIcon;
    //
    @FXML
    private ImageView claimViewButton;
    @FXML
    private ImageView switchStatusButton;
    @FXML
    private ImageView requestViewButton;
    @FXML
    private ImageView approveDeclineRequestButton;

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
