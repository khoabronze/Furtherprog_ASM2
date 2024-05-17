package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
public class SearchClaimController {
    private ClaimService claimService;

    @FXML
    private TextField Claim_ID_Box;
    @FXML
    private Button search_button;

    private String claimIdData;
    public SearchClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }
    public void search() throws IOException {
        claimIdData = Claim_ID_Box.getText();
        Optional<Claim> optionalClaim = claimService.getClaim(claimIdData);
        if (optionalClaim.isPresent()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update-Claim-Form.fxml"));
            Parent newSceneParent = loader.load();

            // Get the controller of the scene
            UpdateClaimController controller = loader.getController();
            // Pass the claim id to the controller
            controller.initializeData(claimIdData);

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) search_button.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No Claim found with the provided claim id.");
            alert.showAndWait();

            // Clear the claim id input
            Claim_ID_Box.clear();
        }
    }
    //    go to homepage
    @FXML
    private HBox homeHBox;
    @FXML
    private void handleHomeNavigation(MouseEvent event) {
        navigateToHomePage();
    }

    @FXML
    private void navigateToHomePage() {
        try {
            Stage stage = (Stage) homeHBox.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/furtherprog_asm2/Homepage-Admin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    go to user page
    @FXML
    private HBox userIcon;

    @FXML
    private void handleUserIconClick(MouseEvent event) {
        navigateToUserProfile();
    }
    @FXML
    private void navigateToUserProfile() {
        try {
            Stage stage = (Stage) userIcon.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/furtherprog_asm2/UserProfile.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
