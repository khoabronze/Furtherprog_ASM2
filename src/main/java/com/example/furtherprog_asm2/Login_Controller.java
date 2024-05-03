package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {
    @FXML
    private TextField Username_box;

    @FXML
    private PasswordField Password_box;

    @FXML
    private Button login_button;

    private Db_function db = new Db_function();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_button.setOnAction(event -> {
            String username = Username_box.getText();
            String password = Password_box.getText();

            if (login(username, password)) {
                System.out.println("Login successful");
                // Navigate to the next page
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        });
    }

    private boolean login(String username, String password) {
        Connection conn = db.connect_to_db();
        String query = "SELECT * FROM Admin WHERE Username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password"); // replace "password" with your actual password column name
                if (dbPassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }
}