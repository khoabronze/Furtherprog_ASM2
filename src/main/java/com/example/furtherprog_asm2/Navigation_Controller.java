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
    @FXML
    private ImageView viewRequestIcon;
    @FXML
    private ImageView updatePolicyHolderIcon;
    @FXML
    private ImageView updateDependentIcon;
    @FXML
    private ImageView updateClaimIcon;
    @FXML
    private ImageView createClaimIcon;
    @FXML
    private ImageView createDependentIcon;
    @FXML
    private ImageView deleteDependentIcon;
    @FXML
    private ImageView deleteClaimIcon;
    @FXML
    private ImageView claimViewButton;
    @FXML
    private ImageView switchStatusButton;
    @FXML
    private ImageView requestViewButton;
    @FXML
    private ImageView approveDeclineRequestButton;

    // -------------------------------------------------------------------------------------------
    // Insurance Surveyor buttons and logos
    @FXML
    private ImageView claimInsuranceSurveyorIcon;
    @FXML
    private ImageView requestInsuranceSurveyorIcon;
    @FXML
    private ImageView customersInsuranceSurveyorIcon;
    @FXML
    private ImageView homeInsuranceSurveyorIcon;

    // -------------------------------------------------------------------------------------------
    // Insurance Manager buttons and logos
    @FXML
    private ImageView claimInsuranceManagerIcon;
    @FXML
    private ImageView homeInsuranceManagerIcon;

    // -------------------------------------------------------------------------------------------
    // Dependent buttons and logos
    @FXML
    private ImageView homeDependentIcon;

    // -------------------------------------------------------------------------------------------
    // Policy Holder buttons and logos
    @FXML
    private ImageView policyHolderPolicyHolderIcon;
    @FXML
    private ImageView dependentPolicyHolderIcon;
    @FXML
    private ImageView claimPolicyHolderIcon;
    @FXML
    private ImageView homePolicyHolderIcon;

    // -------------------------------------------------------------------------------------------
    // Policy Owner buttons and logos
    @FXML
    private ImageView beneficiariesPolicyOwnerIcon;
    @FXML
    private ImageView claimPolicyOwnerIcon;
    @FXML
    private ImageView homePolicyOwnerIcon;

    // -------------------------------------------------------------------------------------------
    // Navigation between pages (Main)
    @FXML
    public void navigateTableViewDisplayInsuranceCard() throws IOException {
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
    public void navigateViewPO() throws IOException {
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
    public void navigateViewPH() throws IOException {
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
    public void navigateGetAllDependent() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Get_all_dependent.fxml"));

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
    @FXML
    public void navigateTableViewDisplayRequest() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("TableView-Display-Request.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) viewRequestIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateUpdatePHSearch() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Update_PH_search.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) updatePolicyHolderIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateUpdateDependentSearch() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Update_dependent_search.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) updateDependentIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateUpdateClaimSearch() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Update-Claim-Search.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) updateClaimIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateCreateClaimForm() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Create-Claim-Form.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) createClaimIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateCreateDependentForm() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Create-Dependent-Form.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) createDependentIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateDeleteClaimForm() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Delete-Claim-Form.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) deleteClaimIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateDeleteDependent() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Delete_dependent.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) deleteDependentIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void handleClaimViewNavigation() throws IOException {
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
    public void handleSwitchStatusNavigation() throws IOException {
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
    public void handleRequestFormNavigation() throws IOException {
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
    public void handleRequestViewNavigation() throws IOException {
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
    public void handleApproveDeclineRequest() throws IOException{
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Approve-Decline-NavBar.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) approveDeclineRequestButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    // -------------------------------------------------------------------------------------------
    // Navigation between pages (Insurance Surveyor)
    @FXML
    public void navigateClaimMenuInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Claim-Menu-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) claimInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateRequestMenuInsuranceSurveyor() throws IOException {
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
    public void navigateCustomersMenuInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Customers-Menu-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) customersInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateHomepageInsuranceSurveyor() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Homepage-InsuranceSurveyor.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) homeInsuranceSurveyorIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    // -------------------------------------------------------------------------------------------
    // Navigate between pages (Insurance Manager)
    @FXML
    public void navigateClaimMenuInsuranceManager() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Claim-Menu-InsuranceManager.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) claimInsuranceManagerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateHomepageInsuranceManager() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Homepage-InsuranceManager.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) homeInsuranceManagerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    // -------------------------------------------------------------------------------------------
    // Navigation between pages (Dependent)
    @FXML
    public void navigateHomepageDependent() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Homepage-Dependent.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) homeDependentIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    // -------------------------------------------------------------------------------------------
    // Navigation between pages (Policy Holder)
    @FXML
    public void navigatePolicyHolderMenuPolicyHolder() throws IOException {
        // Load the FXML file for the new scene
        try {
            Parent newSceneParent = FXMLLoader.load(getClass().getResource("PolicyHolder-Menu-PolicyHolder.fxml"));

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) policyHolderPolicyHolderIcon.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void navigateDependentMenuPolicyHolder() throws IOException {
        // Load the FXML file for the new scene
        try {
            Parent newSceneParent = FXMLLoader.load(getClass().getResource("Dependent-Menu-PolicyHolder.fxml"));

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) dependentPolicyHolderIcon.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void navigateClaimMenuPolicyHolder() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Claim-Menu-PolicyHolder.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) claimPolicyHolderIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateHomepagePolicyHolder() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Homepage-PolicyHolder.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) homePolicyHolderIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    // -------------------------------------------------------------------------------------------
    // Navigation between pages (Policy Owner)
    @FXML
    public void navigateDependentMenuPolicyOwner() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Dependent-Menu-PolicyOwner.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) beneficiariesPolicyOwnerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
    @FXML
    public void navigateClaimMenuPolicyOwner() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Claim-Menu-PolicyOwner.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) claimPolicyOwnerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    @FXML
    public void navigateHomepagePolicyOwner() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Homepage-PolicyOwner.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) homePolicyOwnerIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
}
