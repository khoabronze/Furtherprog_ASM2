package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {
    @FXML
    private TextField Username_box;

    @FXML
    private PasswordField Password_box;

    @FXML
    private Button login_button;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code goes here
    }
}