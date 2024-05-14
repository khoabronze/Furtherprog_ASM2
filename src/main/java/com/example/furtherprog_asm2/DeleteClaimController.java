package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class DeleteClaimController {
    @FXML
    private TextField ID_DELETE_BOX;

    private ClaimService claimService;

    public DeleteClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void Delete(){
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()){
            return;
        }
        Claim claim = claimService.getClaim(id);
        if (claim != null){
            claimService.DeleteClaim(claim);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Claim Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The claim has been deleted successfully.");
            alert.showAndWait();
        }
    }
}