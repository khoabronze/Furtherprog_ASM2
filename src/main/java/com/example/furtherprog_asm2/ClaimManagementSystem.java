package com.example.furtherprog_asm2;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

public class ClaimManagementSystem {

    @FXML
    private Button addButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TableView<Claim> claimsTable;

    @FXML
    private void initialize() {
        // Optional: Initialize UI components or data here
    }

    @FXML
    private void handleAddButtonAction() {
        // Logic to add a claim
    }

    // Additional methods for other buttons and functionalities
}