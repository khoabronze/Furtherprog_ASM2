package com.example.furtherprog_asm2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class Get_PO_Controller {

    private PolicyOwnerService policyOwnerService;

    public Get_PO_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyOwnerService = new PolicyOwnerService(new PolicyOwnerDao_IMP(connection));
    }

    @FXML
    private TableView<PolicyOwner> tableView;
    @FXML
    private TableColumn<PolicyOwner, String> idColumn;
    @FXML
    private TableColumn<PolicyOwner, String> nameColumn;
    @FXML
    private TableColumn<PolicyOwner, String> phoneColumn;
    @FXML
    private TableColumn<PolicyOwner, String> mailColumn;
    @FXML
    private TableColumn<PolicyOwner, String> addressColumn;
    @FXML
    private TableColumn<PolicyOwner, String> passColumn;

    @FXML
    private TextField ID_BOX;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public void handleSearchButtonAction() {
        String id = ID_BOX.getText();
        Optional<PolicyOwner> optionalPolicyOwner = policyOwnerService.getPolicyOwner(id);
        if (optionalPolicyOwner.isPresent()) {
            displayPolicyOwner(optionalPolicyOwner.get());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("Policy Owner not found");
            alert.showAndWait();
        }
    }

    public void displayPolicyOwner(PolicyOwner policyOwner) {
        ObservableList<PolicyOwner> data = FXCollections.observableArrayList();
        if (policyOwner != null) {
            data.add(policyOwner);
        }
        tableView.setItems(data);
    }

    public void Viewall() {
        List<PolicyOwner> policyOwners = policyOwnerService.getAllPolicyOwners();
        ObservableList<PolicyOwner> data = FXCollections.observableArrayList(policyOwners);
        tableView.setItems(data);
    }
}