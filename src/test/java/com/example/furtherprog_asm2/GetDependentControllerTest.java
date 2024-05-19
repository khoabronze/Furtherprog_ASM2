package com.example.furtherprog_asm2;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
class GetDependentControllerTest {
    @Mock
    private DependentService dependentService;

    @Mock
    private TableView<Dependent> tableView;

    @Mock
    private TextField ID_BOX;

    @InjectMocks
    private GetDependentController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleSearchButtonAction_dependentFound() {
        String id = "123";
        Dependent dependent = new Dependent();
        when(ID_BOX.getText()).thenReturn(id);
        when(dependentService.getOneDependent(id)).thenReturn(dependent);

        controller.handleSearchButtonAction();

        verify(controller).displayDependent(dependent);
    }

    @Test
    public void testHandleSearchButtonAction_dependentNotFound() {
        String id = "123";
        when(ID_BOX.getText()).thenReturn(id);
        when(dependentService.getOneDependent(id)).thenReturn(null);

        controller.handleSearchButtonAction();

        verify(controller, never()).displayDependent(any());
        verifyAlertShown("Dependent not found");
    }

    @Test
    public void testViewAll() {
        List<Dependent> dependents = List.of(new Dependent(), new Dependent());
        when(dependentService.getAllDependents()).thenReturn(dependents);

        controller.Viewall();

        verify(tableView).setItems(any(ObservableList.class));
    }

    private void verifyAlertShown(String contentText) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Search Result");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}