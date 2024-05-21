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
import java.util.HashMap;

public class Create_PH_controller {
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
    private PolicyHolder_Service policyHolderService;

    public Create_PH_controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyHolderService = new PolicyHolder_Service(new PolicyHolderDAO_IMP(connection));
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
        if (!ID.matches("PH-\\d{10}")) {
            showAlert("Error Dialog", "Input Error", "ID must be in the format 'PH-10 numbers'");
            return;
        }
        // Create a new InsuranceCard object
        InsuranceCard insuranceCard = new InsuranceCard();

        // Create a new PolicyHolder object
        PolicyHolder policyHolder = new PolicyHolder(ID, Name, Phone, Mail, Address, Password, insuranceCard, new ArrayList<>(), "PolicyHolder", new HashMap<>());

        policyHolderService.addPolicyHolder(policyHolder);
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