package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Controller {
    @FXML
    private TextField Username_box;

    @FXML
    private PasswordField Password_box;

    @FXML
    private Button login_button;

    private Db_function db = new Db_function();

    @FXML
    public void login() {
        String Username = Username_box.getText();
        String Password = Password_box.getText();

        Connection con = db.connect_to_db();
        String[] roles = {"Admin", "Dependent", "PolicyHolder", "PolicyOwner", "InsuranceManager", "InsuranceSurveyor"};
        String role = null;
        for (String database : roles) {
            String query = "SELECT * FROM \"" + database + "\" WHERE \"Username\" = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, Username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String dbPassword = rs.getString("Password");
                    if (dbPassword.equals(Password)) {
                        System.out.println("Login successful");
                        role = database;
                        break;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (role != null) {
            // Navigate to the appropriate homepage based on the role
            switch (role) {
                case "Admin":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage-Admin.fxml"));
                        Stage stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Dependent":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage-Dependent.fxml"));
                        Stage stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "PolicyHolder":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage-PolicyHolder.fxml"));
                        Stage stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "PolicyOwner":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage-PolicyOwner.fxml"));
                        Stage stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "InsuranceManager":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage-InsuranceManager.fxml"));
                        Stage stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "InsuranceSurveyor":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage-InsuranceSurveyor.fxml"));
                        Stage stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    // handle unknown role
                    break;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }
}