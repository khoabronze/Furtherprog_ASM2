package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class AddDependentControllerTest {

    @Mock
    private DependentService dependentService;

    @InjectMocks
    private ADD_Dependent_Controller addDependentController;

    @BeforeEach
    public void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            // Initialization logic if needed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        addDependentController.getName_Form().setText("");
        addDependentController.getID_Form().setText("");
        addDependentController.getPhone_Form().setText("");
        addDependentController.getMail_Form().setText("");
        addDependentController.getAddress_Form().setText("");
        addDependentController.getPassword_Form().setText("");
    }

    @Test
    public void testAddDependentSuccess() {
        // Arrange
        addDependentController.getName_Form().setText("John Doe");
        addDependentController.getID_Form().setText("1");
        addDependentController.getPhone_Form().setText("1234567890");
        addDependentController.getMail_Form().setText("john.doe@example.com");
        addDependentController.getAddress_Form().setText("123 Main St");
        addDependentController.getPassword_Form().setText("password");

        when(dependentService.addDependent(any(Dependent.class))).thenReturn(true);

        // Act
        addDependentController.Create();

        // Assert
        verify(dependentService, times(1)).addDependent(any(Dependent.class));
    }

    @Test
    public void testAddDependentFailure() {
        // Arrange
        addDependentController.getName_Form().setText("John Doe");
        addDependentController.getID_Form().setText("1");
        addDependentController.getPhone_Form().setText("1234567890");
        addDependentController.getMail_Form().setText("john.doe@example.com");
        addDependentController.getAddress_Form().setText("123 Main St");
        addDependentController.getPassword_Form().setText("password");

        when(dependentService.addDependent(any(Dependent.class))).thenReturn(false);

        // Act
        addDependentController.Create();

        // Assert
        verify(dependentService, times(1)).addDependent(any(Dependent.class));
    }

    @Test
    public void testAddDependentWithEmptyFields() {
        // Arrange
        addDependentController.getName_Form().setText("");
        addDependentController.getID_Form().setText("");
        addDependentController.getPhone_Form().setText("");
        addDependentController.getMail_Form().setText("");
        addDependentController.getAddress_Form().setText("");
        addDependentController.getPassword_Form().setText("");

        // Act
        addDependentController.Create();

        // Assert
        verify(dependentService, times(0)).addDependent(any(Dependent.class));
    }
}
