package com.example.furtherprog_asm2;

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

public class Delete_PO_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField ID_DELETE_BOX;

    private PolicyOwnerService policyOwnerService;

    public Delete_PO_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyOwnerService = new PolicyOwnerService(new PolicyOwnerDao_IMP(connection));
    }


    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<PolicyOwner> optionalPolicyOwner = policyOwnerService.getPolicyOwner(id);
        if (optionalPolicyOwner.isPresent()) {
            PolicyOwner policyOwner = optionalPolicyOwner.get();
            policyOwnerService.deletePolicyOwner(policyOwner);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Policy Owner Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The policy owner has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Policy Owner Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No policy owner with the provided ID was found.");
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
}