package com.example.furtherprog_asm2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Profile_Controller implements Initializable {
    @FXML
    private TextField idData;
    @FXML
    private TextField nameData;
    @FXML
    private TextField phoneData;
    @FXML
    private TextField addressData;
    @FXML
    private TextField emailData;
    @FXML
    private TextField passwordData;
    @FXML
    private ImageView homeIcon;
    @FXML
    private Button goBackButton;
    @FXML
    private Button logOutButton;

    private Db_function db = new Db_function();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeUserData();
    }

    // Render back to exact homepage based on its role
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
    public void handleGoBackButton() {
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
                Stage stage = (Stage) goBackButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleLogOutClick() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) logOutButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }

    // Initialize Data
    public void initializeUserData() {
        String loggedInRole = Login_Controller.loggedInRole;
        String userPassword = null;

        try {
            Connection connection = db.connect_to_db();
            Statement statement = connection.createStatement();
            String sql = null;

            // Get the password of the logged in user from the respective role's table
            switch (loggedInRole) {
                case "Admin":
                    sql = "SELECT password FROM \"Admin\"";
                    break;
                case "Dependent":
                    sql = "SELECT password FROM \"Dependent\"";
                    break;
                case "Policy Holder":
                    sql = "SELECT password FROM \"Policy Holder\"";
                    break;
                case "Policy Owner":
                    sql = "SELECT password FROM \"Policy Owner\"";
                    break;
                case "Insurance Manager":
                    sql = "SELECT password FROM \"Insurance Manager\"";
                    break;
                case "Insurance Surveyor":
                    sql = "SELECT password FROM \"Insurance Surveyor\"";
                    break;
                default:
                    // handle unknown role
                    break;
            }

            if (sql != null) {
                ResultSet resultSetPassword = statement.executeQuery(sql);

                if (resultSetPassword.next()) {
                    userPassword = resultSetPassword.getString("password");
                }

                resultSetPassword.close();
            }

            // If the password is not null, proceed to check in the "user" table
            if (userPassword != null) {
                sql = "SELECT * FROM \"user\" WHERE role = '" + loggedInRole + "' AND password = '" + userPassword + "'";
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    idData.setText(resultSet.getString("id"));
                    nameData.setText(resultSet.getString("name"));
                    phoneData.setText(resultSet.getString("phone"));
                    addressData.setText(resultSet.getString("address"));
                    emailData.setText(resultSet.getString("email"));
                    passwordData.setText(resultSet.getString("password"));

                    // Set the text fields to non-editable
                    idData.setEditable(false);
                    nameData.setEditable(false);
                    phoneData.setEditable(false);
                    addressData.setEditable(false);
                    emailData.setEditable(false);
                    passwordData.setEditable(false);
                }

                resultSet.close();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
