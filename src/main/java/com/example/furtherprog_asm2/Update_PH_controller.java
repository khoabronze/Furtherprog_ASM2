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

public class Update_PH_controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    @FXML
    private TextField PH_ID_BOX;
    @FXML
    private Button search_button;

    private PolicyHolder_Service policyHolderService;
    private String policyHolderIdData;
    private PolicyHolder originalPolicyHolder;

    @FXML
    private TextField Name_PH_Box;
    @FXML
    private TextField Phone_PH_box;
    @FXML
    private TextField Address_PH_box;
    @FXML
    private TextField Mail_PH_box;
    @FXML
    private TextField Password_PH_box;

    @FXML
    private TextField PH_ID_Box1;

    public Update_PH_controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyHolderService = new PolicyHolder_Service(new PolicyHolderDAO_IMP(connection));
    }

    public void initializeData(String policyHolderId) {
        if (policyHolderId != null) {
            Optional<PolicyHolder> policyHolderOptional = policyHolderService.getPolicyHolder(policyHolderId);
            if (policyHolderOptional.isPresent()) {
                originalPolicyHolder = policyHolderOptional.get();

                PH_ID_Box1.setText(originalPolicyHolder.getId());
                PH_ID_Box1.setEditable(false);

                Name_PH_Box.setText(originalPolicyHolder.getName());
                Name_PH_Box.setEditable(false);

                Phone_PH_box.setText(originalPolicyHolder.getPhone());
                Address_PH_box.setText(originalPolicyHolder.getAddress());
                Mail_PH_box.setText(originalPolicyHolder.getEmail());
                Password_PH_box.setText(originalPolicyHolder.getPassword());

            } else {
                // Handle the case where the policy holder is not found
                System.out.println("Policy Holder not found with id: " + policyHolderId);
            }
        } else {
            System.out.println("Policy Holder id is null");
        }
    }

    public void search() {
        policyHolderIdData = PH_ID_BOX.getText();
        Optional<PolicyHolder> optionalPolicyHolder = policyHolderService.getPolicyHolder(policyHolderIdData);
        if (optionalPolicyHolder.isPresent()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_PH_form.fxml"));
                Parent newSceneParent = loader.load();

                // Get the controller of the scene
                Update_PH_controller controller = loader.getController();
                // Pass the policy holder id to the controller
                controller.initializeData(policyHolderIdData);

                // Create a new scene
                Scene newScene = new Scene(newSceneParent);

                // Get the current stage
                Stage currentStage = (Stage) search_button.getScene().getWindow();

                // Set the new scene on the current stage
                currentStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No Policy Holder found with the provided id.");
            alert.showAndWait();

            // Clear the policy holder id input
            PH_ID_BOX.clear();
        }
    }

    public boolean update()throws IOException  {
        String policyHolderId = PH_ID_Box1.getText();
        String name = Name_PH_Box.getText();
        String phone = Phone_PH_box.getText();
        String address = Address_PH_box.getText();
        String email = Mail_PH_box.getText();
        String password = Password_PH_box.getText();

        if (originalPolicyHolder != null) {
            if (phone.isEmpty()) {
                phone = originalPolicyHolder.getPhone();
            }

            if (address.isEmpty()) {
                address = originalPolicyHolder.getAddress();
            }

            if (email.isEmpty()) {
                email = originalPolicyHolder.getEmail();
            }
            if (password.isEmpty()) {
                password = originalPolicyHolder.getPassword();
            }
        }

        PolicyHolder newPolicyHolder = new PolicyHolder();
        newPolicyHolder.setId(originalPolicyHolder.getId());
        newPolicyHolder.setName(originalPolicyHolder.getName());
        newPolicyHolder.setPhone(phone);
        newPolicyHolder.setAddress(address);
        newPolicyHolder.setEmail(email);
        newPolicyHolder.setPassword(password);

        boolean updateSuccessful = policyHolderService.updatePolicyHolder(newPolicyHolder);
        if (updateSuccessful) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_PH_form.fxml"));
            Parent newSceneParent = loader.load();
            Scene newScene = new Scene(newSceneParent);
            Stage currentStage = (Stage) PH_ID_Box1.getScene().getWindow();
            currentStage.setScene(newScene);

            // Add alert for successful update
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Update successful.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Update failed.");
            alert.showAndWait();
        }

        return updateSuccessful;
    }

    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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