package com.example.furtherprog_asm2;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.util.Optional;

import static org.mockito.Mockito.*;
class Delete_dependent_controllerTest {
    @Mock
    private TextField ID_DELETE_BOX;

    @Mock
    private DependentService dependentService;

    @Mock
    private Alert alert;

    @InjectMocks
    private Delete_dependent_controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDelete_emptyID() {
        when(ID_DELETE_BOX.getText()).thenReturn("");
        controller.Delete();
        verify(dependentService, never()).getDependent(anyString());
    }

    @Test
    public void testDelete_dependentFound() {
        String id = "123";
        when(ID_DELETE_BOX.getText()).thenReturn(id);

        Dependent dependent = new Dependent();
        when(dependentService.getDependent(id)).thenReturn(Optional.of(dependent));

        controller.Delete();

        verify(dependentService, times(1)).deleteDependent(dependent);
        verify(alert, times(1)).setTitle("Dependent Deleted");
        verify(alert, times(1)).setContentText("The dependent has been deleted successfully.");
        verify(alert, times(1)).showAndWait();
    }

    @Test
    public void testDelete_dependentNotFound() {
        String id = "123";
        when(ID_DELETE_BOX.getText()).thenReturn(id);

        when(dependentService.getDependent(id)).thenReturn(Optional.empty());

        controller.Delete();

        verify(alert, times(1)).setTitle("Dependent Not Found");
        verify(alert, times(1)).setContentText("No dependent with the provided ID was found.");
        verify(alert, times(1)).showAndWait();
    }
}