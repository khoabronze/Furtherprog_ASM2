/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ApproveDecline_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField requestIDInput;
    @FXML
    private Button searchRequestButton;
    @FXML
    private TextField rid;
    @FXML
    private TextField id;
    @FXML
    private TextArea note;
    @FXML
    private Button approveButton;
    @FXML
    private Button declineButton;
    @FXML
    private ImageView profileIcon;

    private RequestDAO requestDAO = new RequestDAO();
    private Request originalRequest;

    public void initialize(String requestIDData) {
        originalRequest = requestDAO.get(requestIDData).get();
        rid.setText(originalRequest.getRid());
        id.setText(originalRequest.getId());
        note.setText(originalRequest.getNote());
        // set information to be uneditable
        rid.setEditable(false);
        id.setEditable(false);
        note.setEditable(false);
    }

    @FXML
    public void handleSearchRequest() throws IOException {
        String requestIDData = requestIDInput.getText();
        RequestDAO requestDAO = new RequestDAO();
        Optional<Request> optionalRequest = requestDAO.get(requestIDData);
        if (optionalRequest.isPresent()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Approve-Decline-Form.fxml"));
            Parent newSceneParent = loader.load();

            // Get the controller of the scene
            ApproveDecline_Controller controller = loader.getController();
            // Pass the card number to the controller
            controller.initialize(requestIDData);

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) searchRequestButton.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        }
    }

    @FXML
    public void handleApproveRequest() {
        try {
            // Set the approval status to "APPROVE"
            originalRequest.setApproval(Approval.APPROVED);

            // Update the request in the database
            requestDAO.update(originalRequest);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Request approved successfully.");
            alert.showAndWait();

            // Load the Approve-Decline-NavBar.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Approve-Decline-NavBar.fxml"));
            Parent newSceneParent = loader.load();

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) approveButton.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } catch (Exception e) {
            // Display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while approving the request: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void handleDeclineRequest() {
        try {
            // Set the approval status to "DECLINE"
            originalRequest.setApproval(Approval.DECLINED);

            // Update the request in the database
            requestDAO.update(originalRequest);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Request declined successfully.");
            alert.showAndWait();

            // Load the Approve-Decline-NavBar.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Approve-Decline-NavBar.fxml"));
            Parent newSceneParent = loader.load();

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) declineButton.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } catch (Exception e) {
            // Display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while declining the request: " + e.getMessage());
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
