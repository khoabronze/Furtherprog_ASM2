package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.Claim;
import com.example.furtherprog_asm2.ClaimDAO;
import com.example.furtherprog_asm2.ClaimService;
import com.example.furtherprog_asm2.Db_function;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

public class DeleteClaimController {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    @FXML
    private TextField ID_DELETE_BOX;

    private ClaimService claimService;

    public DeleteClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<Claim> optionalClaim = claimService.getClaim(id);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claimService.deleteClaim(claim);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Claim Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The claim has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Claim Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No claim with the provided ID was found.");
            alert.showAndWait();
        }
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