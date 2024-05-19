package com.example.furtherprog_asm2;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
class RequestClaim_ControllerTest {
    @Mock
    private TextField rid;
    @Mock
    private Alert alert;
    @Mock
    private TextField id;

    @Mock
    private TextArea note;

    @Mock
    private Button requestButton;

    @InjectMocks
    private RequestClaim_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessRequest_validRid() {
        String validRid = "r-123";
        when(rid.getText()).thenReturn(validRid);
        when(id.getText()).thenReturn("123");
        when(note.getText()).thenReturn("Test note");

        RequestDAO requestDAO = mock(RequestDAO.class);
        when(requestDAO.create(any(Request.class))).thenReturn(true);

        controller.processRequest();

        verify(requestDAO, times(1)).create(any(Request.class));
        verify(rid).clear();
        verify(id).clear();
        verify(note).clear();
        verifyAlertShown(Alert.AlertType.INFORMATION, "Request successfully");

    }

    @Test
    public void testProcessRequest_invalidRid() {
        String invalidRid = "123";
        when(rid.getText()).thenReturn(invalidRid);

        controller.processRequest();

        verify(rid).clear();
        verify(id, never()).clear();
        verify(note, never()).clear();
        verifyAlertShown(Alert.AlertType.ERROR, "Invalid rid. It must start with 'r-' followed by 3 digits.");
        verifyNoMoreInteractions(requestButton);
    }

    @Test
    public void testProcessRequest_databaseError() {
        String validRid = "r-123";
        when(rid.getText()).thenReturn(validRid);
        when(id.getText()).thenReturn("123");
        when(note.getText()).thenReturn("Test note");

        RequestDAO requestDAO = mock(RequestDAO.class);
        when(requestDAO.create(any(Request.class))).thenReturn(false);

        controller.processRequest();

        verify(requestDAO, times(1)).create(any(Request.class));
        verify(rid).clear();
        verify(id).clear();
        verify(note).clear();
        verifyAlertShown(Alert.AlertType.ERROR, "Request failed");
        verifyNoMoreInteractions(requestButton);
    }

    private void verifyAlertShown(Alert.AlertType type, String message) {
        verify(alert, times(1)).setTitle(type == Alert.AlertType.ERROR ? "Error" : "Success");
        verify(alert, times(1)).setHeaderText(null);
        verify(alert, times(1)).setContentText(message);
        verify(alert, times(1)).showAndWait();
    }


}