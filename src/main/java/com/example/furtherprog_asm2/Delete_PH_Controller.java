package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.Optional;

public class Delete_PH_Controller {
    @FXML
    private TextField ID_DELETE_BOX;

    private PolicyHolder_Service policyHolderService;

    public Delete_PH_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyHolderService = new PolicyHolder_Service(new PolicyHolderDAO_IMP(connection));
    }

    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<PolicyHolder> optionalPolicyHolder = policyHolderService.getPolicyHolder(id);
        if (optionalPolicyHolder.isPresent()) {
            PolicyHolder policyHolder = optionalPolicyHolder.get();
            policyHolderService.deletePolicyHolder(policyHolder);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Policy Holder Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The policy holder has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Policy Holder Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No policy holder with the provided ID was found.");
            alert.showAndWait();
        }
    }
}