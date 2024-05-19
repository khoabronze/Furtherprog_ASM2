package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.Optional;
import static org.mockito.Mockito.*;
class Update_Depedent_ControllerTest {
    @Mock
    private TextField Dependent_ID_Box;
@Mock
private Alert alert;
    @Mock
    private Button search_button;

    @Mock
    private DependentService dependentService;

    @InjectMocks
    private Update_Depedent_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch_DependentFound() throws IOException {
        // Given
        String dependentId = "123456";
        when(Dependent_ID_Box.getText()).thenReturn(dependentId);
        Dependent dependent = new Dependent();
        Optional<Dependent> optionalDependent = Optional.of(dependent);
        when(dependentService.getDependent(dependentId)).thenReturn(optionalDependent);
        FXMLLoader loader = mock(FXMLLoader.class);
        when(loader.load()).thenReturn(mock(Parent.class));
        Update_Depedent_Controller updateDependentController = mock(Update_Depedent_Controller.class);
        when(loader.getController()).thenReturn(updateDependentController);
        Stage currentStage = mock(Stage.class);
        Scene newScene = mock(Scene.class);
        when(newScene.getWindow()).thenReturn(currentStage);
        when(search_button.getScene()).thenReturn(newScene);

        // When
        controller.search();

        // Then
        verify(updateDependentController).initializeData(dependentId);
        verify(currentStage).setScene(newScene);
    }

    @Test
    public void testSearch_DependentNotFound() throws IOException {
        // Given
        String dependentId = "123456";
        when(Dependent_ID_Box.getText()).thenReturn(dependentId);
        Optional<Dependent> optionalDependent = Optional.empty();
        when(dependentService.getDependent(dependentId)).thenReturn(optionalDependent);
        controller.search();
        Alert alertMock = mock(Alert.class);
        verify(alertMock).setTitle("Error");
        verify(alertMock).setHeaderText(null);
        verify(alertMock).setContentText("No Dependent found with the provided dependent id.");
        verify(alertMock).showAndWait();
        verify(Dependent_ID_Box).clear();
    }

    @Test
    public void testUpdate_Successful() throws IOException {
        // Given
        String dependentId = "123456";
        String name = "John Doe";
        String phone = "123456789";
        String address = "123 Main St";
        String email = "john.doe@example.com";
        String password = "password";
        Dependent newDependent = new Dependent();
        newDependent.setId(dependentId);
        newDependent.setName(name);
        newDependent.setPhone(phone);
        newDependent.setAddress(address);
        newDependent.setEmail(email);
        newDependent.setPassword(password);
        when(dependentService.updateDependent(any(Dependent.class))).thenReturn(true);
        FXMLLoader loader = mock(FXMLLoader.class);
        when(loader.load()).thenReturn(mock(Parent.class));
        Stage currentStage = mock(Stage.class);
        Scene newScene = mock(Scene.class);
        when(newScene.getWindow()).thenReturn(currentStage);
        when(this.Dependent_ID_Box.getScene()).thenReturn(newScene);
        boolean updateResult = controller.update();
        assertTrue(updateResult);
        verify(alert).setTitle("Success");
        verify(alert).setHeaderText(null);
        verify(alert).setContentText("Update successful.");
        verify(alert).showAndWait();
        verify(currentStage).setScene(newScene);
    }

    @Test
    public void testUpdate_Failed() throws IOException {
        // Given
        String dependentId = "123456";
        String name = "John Doe";
        String phone = "123456789";
        String address = "123 Main St";
        String email = "john.doe@example.com";
        String password = "password";
        Dependent newDependent = new Dependent();
        newDependent.setId(dependentId);
        newDependent.setName(name);
        newDependent.setPhone(phone);
        newDependent.setAddress(address);
        newDependent.setEmail(email);
        newDependent.setPassword(password);
        when(dependentService.updateDependent(any(Dependent.class))).thenReturn(false);
        Alert alertMock = mock(Alert.class);


        // When
        boolean updateResult = controller.update();

        assert(!updateResult);
        verify(alertMock).setTitle("Error");
        verify(alertMock).setHeaderText(null);
        verify(alertMock).setContentText("Update failed.\"");
        verify(alertMock).showAndWait();

    }
}