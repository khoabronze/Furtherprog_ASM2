package com.example.furtherprog_asm2;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ADD_Dependent_ControllerTest {
    @Mock
    private TextFieldWrapper Name_Form;
    @Mock
    private TextFieldWrapper ID_Form;
    @Mock
    private TextFieldWrapper Phone_Form;
    @Mock
    private TextFieldWrapper Mail_Form;
    @Mock
    private TextFieldWrapper Address_Form;
    @Mock
    private PasswordFieldWrapper Password_Form;

    @Mock
    private DependentService depService;

    @InjectMocks
    private ADD_Dependent_Controller controller;

    @BeforeAll
    static void initToolkit() {
        // Ensure JavaFX toolkit is initialized
        if (!Platform.isFxApplicationThread()) {
            new JFXPanel(); // Initializes the JavaFX toolkit
            Platform.startup(() -> {});
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmptyFieldsShowAlert() {
        when(Name_Form.getText()).thenReturn("");
        when(ID_Form.getText()).thenReturn("123");
        when(Phone_Form.getText()).thenReturn("1234567890");
        when(Mail_Form.getText()).thenReturn("test@mail.com");
        when(Address_Form.getText()).thenReturn("123 Street");
        when(Password_Form.getText()).thenReturn("password");

        controller.Create();

        verify(depService, never()).addDependent(any());
    }

    @Test
    public void testCreateAllFieldsFilledAddDependentCalled() {
        when(Name_Form.getText()).thenReturn("John Doe");
        when(ID_Form.getText()).thenReturn("123");
        when(Phone_Form.getText()).thenReturn("1234567890");
        when(Mail_Form.getText()).thenReturn("test@mail.com");
        when(Address_Form.getText()).thenReturn("123 Street");
        when(Password_Form.getText()).thenReturn("password");

        controller.Create();

        verify(depService, times(1)).addDependent(any(Dependent.class));
    }

    @Test
    public void testCreateShowAlertCalledWhenFieldsEmpty() {
        when(Name_Form.getText()).thenReturn("");
        when(ID_Form.getText()).thenReturn("123");
        when(Phone_Form.getText()).thenReturn("1234567890");
        when(Mail_Form.getText()).thenReturn("test@mail.com");
        when(Address_Form.getText()).thenReturn("123 Street");
        when(Password_Form.getText()).thenReturn("password");

        ADD_Dependent_Controller spyController = spy(controller);
        doNothing().when(spyController).showAlert(anyString(), anyString(), anyString());
        spyController.Create();
        verify(spyController, times(1)).showAlert(eq("Error Dialog"), eq("Input Error"), eq("All fields are required"));
        verify(depService, never()).addDependent(any());
    }
}
