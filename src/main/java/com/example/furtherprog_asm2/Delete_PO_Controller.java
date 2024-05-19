package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.Optional;

public class Delete_PO_Controller {
    @FXML
    private TextField ID_DELETE_BOX;

    private PolicyOwnerService policyOwnerService;

    public Delete_PO_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyOwnerService = new PolicyOwnerService(new PolicyOwnerDao_IMP(connection));
    }


    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<PolicyOwner> optionalPolicyOwner = policyOwnerService.getPolicyOwner(id);
        if (optionalPolicyOwner.isPresent()) {
            PolicyOwner policyOwner = optionalPolicyOwner.get();
            policyOwnerService.deletePolicyOwner(policyOwner);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Policy Owner Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The policy owner has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Policy Owner Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No policy owner with the provided ID was found.");
            alert.showAndWait();
        }
    }
}