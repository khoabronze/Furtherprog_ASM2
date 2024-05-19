package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.Optional;

public class Delete_dependent_controller {
    @FXML
    private TextField ID_DELETE_BOX;

    private DependentService dependentService;

    public Delete_dependent_controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.dependentService = new DependentService(new DependentDAO_IMP(connection));
    }


    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<Dependent> optionalDependent = dependentService.getDependent(id);
        if (optionalDependent.isPresent()) {
            Dependent dependent = optionalDependent.get();
            dependentService.deleteDependent(dependent);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dependent Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The dependent has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dependent Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No dependent with the provided ID was found.");
            alert.showAndWait();
        }
    }
}