package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAsCustomer_Controller {
    @FXML
    private TextField Username_box;
    @FXML
    private PasswordField Password_box;

    private Db_function db = new Db_function();

    @FXML
    private void loginAsCustomer(ActionEvent event) {


        String username = Username_box.getText();
        String password = Password_box.getText();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        Connection conn = db.connect_to_db();
        String[] tables = {"Dependent", "InsuranceManager", "InsuranceSurveyor", "PolicyOwner", "TheSystemAdmin"};

        for (String table : tables) {
            String query = "SELECT * FROM \"" + table + "\" WHERE \"Username\" = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String dbPassword = rs.getString("Password");
                    if (dbPassword.equals(password)) {
                        System.out.println("Login successful as " + table);
                        // Navigate to the next page
                        return;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Login Failed");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid username or password");
                        alert.showAndWait();
                    }
                }

//                else {
//                    System.out.println("No matching user found in table: " + table);
//                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    // method to print usernames and passwords from tables
    public void printDependentUsernamesAndPasswords() {
        Connection conn = db.connect_to_db();
        String query = "SELECT \"Username\", \"Password\" FROM \"Dependent\""; // replace "Dependent" with any table name to check

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Usernames and Passwords from Dependent table:");

            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                System.out.println("Username: " + username + ", Password: " + password);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
