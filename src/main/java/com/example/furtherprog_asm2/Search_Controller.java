package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Search_Controller {
    @FXML
    private TextField cardNumberInput;
    @FXML
    private TableView<InsuranceCard> tableView;
    @FXML
    private TableColumn<InsuranceCard, String> cardNumber;
    @FXML
    private TableColumn<InsuranceCard, String> cardHolder;
    @FXML
    private TableColumn<InsuranceCard, String> policyOwner;
    @FXML
    private TableColumn<InsuranceCard, String> expirationDate;

    @FXML
    public void initialize() {
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        cardHolder.setCellValueFactory(new PropertyValueFactory<>("cardHolder"));
        policyOwner.setCellValueFactory(new PropertyValueFactory<>("policyOwner"));
        expirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
    }

    @FXML
    public void search(ActionEvent event) {
        String cardNumberData = cardNumberInput.getText();
        InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
        InsuranceCard insuranceCard = insuranceCardDao.getOne(cardNumberData);
        if (insuranceCard != null) {
            tableView.getItems().clear();
            tableView.getItems().add(insuranceCard);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Card number does not exist.");
            alert.showAndWait();
        }
        cardNumberInput.clear(); // Clear the input field at the search bar
    }
}