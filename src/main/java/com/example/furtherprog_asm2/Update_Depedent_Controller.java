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

public class Update_Depedent_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    @FXML
    private TextField Dependent_ID_Box;
    @FXML
    private Button search_button;

    private DependentService dependentService;
    private String dependentIdData;
    private Dependent originalDependent;


    @FXML
    private TextField Name_Dependent_Box;

    @FXML
    private TextField Phone_Dependent_box;
    @FXML
    private TextField Address_Dependent_box;
    @FXML
    private TextField Mail_dependent_box;
    @FXML
    private TextField Password_dependent_box;

    @FXML
    private TextField Dependent_ID_Box1;

    public Update_Depedent_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.dependentService = new DependentService(new DependentDAO_IMP(connection));
    }
    public void initializeData(String dependentId) {
        if (dependentId != null) {
            Optional<Dependent> dependentOptional = dependentService.getDependent(dependentId);
            if (dependentOptional.isPresent()) {
                originalDependent = dependentOptional.get();

                Dependent_ID_Box1.setText(originalDependent.getId());
                Dependent_ID_Box1.setEditable(false);

                Name_Dependent_Box.setText(originalDependent.getName());
                Name_Dependent_Box.setEditable(false);

                Phone_Dependent_box.setText(originalDependent.getPhone());
                Address_Dependent_box.setText(originalDependent.getAddress());
                Mail_dependent_box.setText(originalDependent.getEmail());
                Password_dependent_box.setText(originalDependent.getPassword());

            } else {
                // Handle the case where the dependent is not found
                System.out.println("Dependent not found with id: " + dependentId);
            }
        } else {
            System.out.println("Dependent id is null");
        }
    }

    public void search() {
        dependentIdData = Dependent_ID_Box.getText();
        Optional<Dependent> optionalDependent = dependentService.getDependent(dependentIdData);
        if (optionalDependent.isPresent()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_dependent_form.fxml"));
                Parent newSceneParent = loader.load();

                // Get the controller of the scene
                Update_Depedent_Controller controller = loader.getController();
                // Pass the dependent id to the controller
                controller.initializeData(dependentIdData);

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
            alert.setContentText("No Dependent found with the provided dependent id.");
            alert.showAndWait();

            // Clear the dependent id input
            Dependent_ID_Box.clear();
        }
    }

    public boolean update()throws IOException  {
        String dependentId = Dependent_ID_Box1.getText();
        String name = Name_Dependent_Box.getText();
        String phone = Phone_Dependent_box.getText();
        String address = Address_Dependent_box.getText();
        String email = Mail_dependent_box.getText();
        String password = Password_dependent_box.getText();

        if (originalDependent != null) {
            if (phone.isEmpty()) {
                phone = originalDependent.getPhone();
            }

            if (address.isEmpty()) {
                address = originalDependent.getAddress();
            }

            if (email.isEmpty()) {
                email = originalDependent.getEmail();
            }
            if (password.isEmpty()) {
                password = originalDependent.getPassword();
            }
        }

        Dependent newDependent = new Dependent();
        newDependent.setId(originalDependent.getId());
        newDependent.setName(originalDependent.getName());
        newDependent.setPhone(phone);
        newDependent.setAddress(address);
        newDependent.setEmail(email);
        newDependent.setPassword(password);

        boolean updateSuccessful = dependentService.updateDependent(newDependent);
        if (updateSuccessful) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_dependent_search.fxml"));
            Parent newSceneParent = loader.load();
            Scene newScene = new Scene(newSceneParent);
            Stage currentStage = (Stage) Dependent_ID_Box1.getScene().getWindow();
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
