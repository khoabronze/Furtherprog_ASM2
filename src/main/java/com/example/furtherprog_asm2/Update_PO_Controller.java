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

public class Update_PO_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    @FXML
    private TextField PO_ID_BOX;
    @FXML
    private Button search_button;

    private PolicyOwnerService policyOwnerService;
    private String policyOwnerIdData;
    private PolicyOwner originalPolicyOwner;

    @FXML
    private TextField Name_PO_Box;
    @FXML
    private TextField Phone_PO_box;
    @FXML
    private TextField Address_PO_box;
    @FXML
    private TextField Mail_PO_box;
    @FXML
    private TextField Password_PO_box;

    @FXML
    private TextField PO_ID_Box1;

    public Update_PO_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyOwnerService = new PolicyOwnerService(new PolicyOwnerDao_IMP(connection));
    }

    public void initializeData(String policyOwnerId) {
        if (policyOwnerId != null) {
            Optional<PolicyOwner> policyOwnerOptional = policyOwnerService.getPolicyOwner(policyOwnerId);
            if (policyOwnerOptional.isPresent()) {
                originalPolicyOwner = policyOwnerOptional.get();

                PO_ID_Box1.setText(originalPolicyOwner.getId());
                PO_ID_Box1.setEditable(false);

                Name_PO_Box.setText(originalPolicyOwner.getName());
                Name_PO_Box.setEditable(false);

                Phone_PO_box.setText(originalPolicyOwner.getPhone());
                Address_PO_box.setText(originalPolicyOwner.getAddress());
                Mail_PO_box.setText(originalPolicyOwner.getEmail());
                Password_PO_box.setText(originalPolicyOwner.getPassword());

            } else {
                // Handle the case where the policy owner is not found
                System.out.println("Policy Owner not found with id: " + policyOwnerId);
            }
        } else {
            System.out.println("Policy Owner id is null");
        }
    }

    public void search() {
        policyOwnerIdData = PO_ID_BOX.getText();
        Optional<PolicyOwner> optionalPolicyOwner = policyOwnerService.getPolicyOwner(policyOwnerIdData);
        if (optionalPolicyOwner.isPresent()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_PO_form.fxml"));
                Parent newSceneParent = loader.load();

                // Get the controller of the scene
                Update_PO_Controller controller = loader.getController();
                // Pass the policy owner id to the controller
                controller.initializeData(policyOwnerIdData);

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
            alert.setContentText("No Policy Owner found with the provided id.");
            alert.showAndWait();

            // Clear the policy owner id input
            PO_ID_BOX.clear();
        }
    }

    public boolean update()throws IOException  {
        String policyOwnerId = PO_ID_Box1.getText();
        String name = Name_PO_Box.getText();
        String phone = Phone_PO_box.getText();
        String address = Address_PO_box.getText();
        String email = Mail_PO_box.getText();
        String password = Password_PO_box.getText();

        if (originalPolicyOwner != null) {
            if (phone.isEmpty()) {
                phone = originalPolicyOwner.getPhone();
            }

            if (address.isEmpty()) {
                address = originalPolicyOwner.getAddress();
            }

            if (email.isEmpty()) {
                email = originalPolicyOwner.getEmail();
            }
            if (password.isEmpty()) {
                password = originalPolicyOwner.getPassword();
            }
        }

        PolicyOwner newPolicyOwner = new PolicyOwner();
        newPolicyOwner.setId(originalPolicyOwner.getId());
        newPolicyOwner.setName(originalPolicyOwner.getName());
        newPolicyOwner.setPhone(phone);
        newPolicyOwner.setAddress(address);
        newPolicyOwner.setEmail(email);
        newPolicyOwner.setPassword(password);

        boolean updateSuccessful = policyOwnerService.updatePolicyOwner(newPolicyOwner);
        if (updateSuccessful) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_PO_search.fxml"));
            Parent newSceneParent = loader.load();
            Scene newScene = new Scene(newSceneParent);
            Stage currentStage = (Stage) PO_ID_Box1.getScene().getWindow();
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
            case "PolicyHolder":
                homepageFile = "Homepage-PolicyHolder.fxml";
                break;
            case "PolicyOwner":
                homepageFile = "Homepage-PolicyOwner.fxml";
                break;
            case "InsuranceManager":
                homepageFile = "Homepage-InsuranceManager.fxml";
                break;
            case "InsuranceSurveyor":
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