package com.example.furtherprog_asm2;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ADD_Dependent_ControllerTest {
    @Mock
    private TextField Name_Form;
    @Mock
    private TextField ID_Form;
    @Mock
    private TextField Phone_Form;
    @Mock
    private TextField Mail_Form;
    @Mock
    private TextField Address_Form;
    @Mock
    private PasswordField Password_Form;

    @Mock
    private DependentService depService;

    @InjectMocks
    private ADD_Dependent_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //check điền thiếu trường thì không cho thêm dependent
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
    // check điền tất cả các trường
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
    //check điền thiếu trường có hiện thông baos k
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