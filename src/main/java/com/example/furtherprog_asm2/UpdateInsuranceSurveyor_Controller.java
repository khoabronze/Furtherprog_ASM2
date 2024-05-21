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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

public class UpdateInsuranceSurveyor_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    @FXML
    private TextField idInput;
    @FXML
    private Button searchButton;
    @FXML
    private TextField idBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField addressBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField passwordBox;
    private InsuranceSurveyor_Service insuranceSurveyorService;
    private Db_function dbFunction;
    private InsuranceSurveyor originalInsuranceSurveyor;

    public UpdateInsuranceSurveyor_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.insuranceSurveyorService = new InsuranceSurveyor_Service(new InsuranceSurveyor_DAO(connection));
    }

    public void initializeData(String insuranceSurveyorId) {
        if (insuranceSurveyorId != null) {
            Optional<InsuranceSurveyor> insuranceSurveyorOptional = insuranceSurveyorService.getInsuranceSurveyor(insuranceSurveyorId);
            if (insuranceSurveyorOptional.isPresent()) {
                originalInsuranceSurveyor = insuranceSurveyorOptional.get();

                idBox.setText(originalInsuranceSurveyor.getId());
                idBox.setEditable(false);

                nameBox.setText(originalInsuranceSurveyor.getName());
                nameBox.setEditable(false);

                phoneBox.setText(originalInsuranceSurveyor.getPhone());
                addressBox.setText(originalInsuranceSurveyor.getAddress());
                emailBox.setText(originalInsuranceSurveyor.getEmail());
                passwordBox.setText(originalInsuranceSurveyor.getPassword());
            } else {
                // Handle the case where the dependent is not found
                System.out.println("Dependent not found with id: " + insuranceSurveyorId);
            }
        } else {
            System.out.println("Dependent id is null");
        }
    }

    @FXML
    public void handleSearchInsuranceSurveyor() {
        String insuranceSurveyorId = idInput.getText();
        Optional<InsuranceSurveyor> optionalInsuranceSurveyor = insuranceSurveyorService.getInsuranceSurveyor(insuranceSurveyorId);
        if (optionalInsuranceSurveyor.isPresent()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update-InsuranceSurveyor-Form.fxml"));
                Parent newSceneParent = loader.load();

                // Get the controller of the scene
                UpdateInsuranceSurveyor_Controller controller = loader.getController();
                // Pass the insuranceSurveyor id to the controller
                controller.initializeData(insuranceSurveyorId);

                // Create a new scene
                Scene newScene = new Scene(newSceneParent);

                // Get the current stage
                Stage currentStage = (Stage) searchButton.getScene().getWindow();

                // Set the new scene on the current stage
                currentStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error Dialog", "Search Result", "An error occurred while searching for the Insurance Surveyor");
            }
        } else {
            showAlert("Error Dialog", "Search Result", "No Insurance Surveyor found with the provided id.");
            // Clear the insuranceSurveyor id input
            idInput.clear();
        }
    }

    @FXML
    public void handleUpdateInsuranceSurveyor() {
        String id = idBox.getText();
        String name = nameBox.getText();
        String phone = phoneBox.getText();
        String address = addressBox.getText();
        String email = emailBox.getText();
        String password = passwordBox.getText();

        if (name.isEmpty()) {
            name = originalInsuranceSurveyor.getName();
        }
        if (phone.isEmpty()) {
            phone = originalInsuranceSurveyor.getPhone();
        }
        if (address.isEmpty()) {
            address = originalInsuranceSurveyor.getAddress();
        }
        if (email.isEmpty()) {
            email = originalInsuranceSurveyor.getEmail();
        }
        if (password.isEmpty()) {
            password = originalInsuranceSurveyor.getPassword();
        }

        InsuranceSurveyor newInsuranceSurveyor = new InsuranceSurveyor();
        newInsuranceSurveyor.setId(id);
        newInsuranceSurveyor.setName(name);
        newInsuranceSurveyor.setPhone(phone);
        newInsuranceSurveyor.setAddress(address);
        newInsuranceSurveyor.setEmail(email);
        newInsuranceSurveyor.setPassword(password);

        boolean updateSuccessful = insuranceSurveyorService.updateInsuranceSurveyor(newInsuranceSurveyor);
        if (updateSuccessful) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update-InsuranceSurveyor-Search.fxml"));
                Parent newSceneParent = loader.load();
                Scene newScene = new Scene(newSceneParent);
                Stage currentStage = (Stage) idBox.getScene().getWindow();
                currentStage.setScene(newScene);

                showAlert("Success Dialog", "Update Result", "Update successful.");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error Dialog", "Update Result", "An error occurred while navigating back to the search page.");
            }
        } else {
            showAlert("Error Dialog", "Update Result", "Update failed.");
        }
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
