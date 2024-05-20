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

public class Delete_dependent_controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField ID_DELETE_BOX;

    private DependentService dependentService;

    public Delete_dependent_controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.dependentService = new DependentService(new DependentDAO_IMP(connection));
    }


    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<Dependent> optionalDependent = dependentService.getDependent(id);
        if (optionalDependent.isPresent()) {
            Dependent dependent = optionalDependent.get();
            dependentService.deleteDependent(dependent);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dependent Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The dependent has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dependent Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No dependent with the provided ID was found.");
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
}