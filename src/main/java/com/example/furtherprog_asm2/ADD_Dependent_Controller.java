package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class ADD_Dependent_Controller {
    private PolicyHolder policyHolder; // the PolicyHolder who is creating the Dependent
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField Name_Form;
    @FXML
    private TextField ID_Form;

    @FXML
    private TextField Phone_Form;

    @FXML
    private TextField Mail_Form;
    @FXML
    private TextField Address_Form;

    @FXML
    private PasswordField Password_Form;
    @FXML
    private ImageView profileIcon;
    private DependentService DepService;
    public ADD_Dependent_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.DepService = new DependentService(new DependentDAO_IMP(connection));
    }
    public void Create(){
        String Name = Name_Form.getText();
        String ID = ID_Form.getText();
        String Phone = Phone_Form.getText();
        String Mail = Mail_Form.getText();
        String Address = Address_Form.getText();
        String Password = Password_Form.getText();

        if (Name.isEmpty() || ID.isEmpty() || Phone.isEmpty() || Mail.isEmpty() || Address.isEmpty() || Password.isEmpty()) {
            showAlert("Error Dialog", "Input Error", "All fields are required");
            return;
        }
        if (!ID.matches("D-\\d{10}")) {
            showAlert("Error Dialog", "Input Error", "ID must be in the format 'D-10 numbers'");
            return;
        }
        // Create a new InsuranceCard object
        InsuranceCard insuranceCard = new InsuranceCard();
        Request request = new Request();

        // Create a new Dependent object
        Dependent dependent = new Dependent(ID, Name, Phone, Mail, Address, Password, insuranceCard, request, new ArrayList<Claim>());
        dependent.setPolicyHolder(policyHolder); // set the Dependent's PolicyHolder

        DepService.addDependent(dependent);
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

