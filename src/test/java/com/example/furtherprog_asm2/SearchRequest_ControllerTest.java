package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
class SearchRequest_ControllerTest {
    @Mock
    private TextField requestIDInput;

    @Mock
    private TableView<Request> tableView;

    @Mock
    private Button searchButton;

    @Mock
    private Button viewAllButton;
    @Mock
    private RequestDAO requestDao;
    @Mock
    private Alert alert;
    @InjectMocks
    private SearchRequest_Controller controller;

    @Test
    public void testHandleSearchOneRequest_RequestFound() {
        String requestId = "123456";
        Request request = new Request(requestId, "userId", "Some note");
        when(requestIDInput.getText()).thenReturn(requestId);
        when(requestDao.getOne(requestId)).thenReturn(request);
        ActionEvent actionEvent = new ActionEvent();
        controller.handleSearchOneRequest(actionEvent);

        verify(tableView).getItems().clear();
        verify(tableView).getItems().add(request);
        verify(requestIDInput).clear();
    }

    @Test
    public void testHandleSearchOneRequest_RequestNotFound() {
        String requestId = "123456";
        when(requestIDInput.getText()).thenReturn(requestId);
        when(requestDao.getOne(requestId)).thenReturn(null);
        ActionEvent actionEvent = new ActionEvent();
        controller.handleSearchOneRequest(actionEvent);

        verify(tableView, never()).getItems().clear();
        verify(tableView, never()).getItems().add(any());
        verify(requestIDInput).clear();

        // Verify that an error alert is shown
        verifyAlertShown("Failure", "Request ID does not exist.");
    }

    @Test
    public void testHandleViewAllRequest() {
        List<Request> requests = new ArrayList<>();
        requests.add(new Request("123456", "userId", "Some note"));
        when(requestDao.getAll()).thenReturn(requests);
        ActionEvent actionEvent = new ActionEvent();
        controller.handleViewAllRequest(actionEvent);

        verify(tableView).getItems().clear();
        verify(tableView).getItems().addAll(requests);
    }

    private void verifyAlertShown(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        verify(alert, times(1)).setTitle(title);
        verify(alert, times(1)).setHeaderText(null);
        verify(alert, times(1)).setContentText(contentText);
        verify(alert, times(1)).showAndWait();
    }
}