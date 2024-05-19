package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class YourControllerTest {

    @Mock
    private TextFieldWrapper textFieldWrapper;

    private YourController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new YourController(textFieldWrapper);
    }

    @Test
    public void testSomeMethod() {
        controller.someMethod();
        verify(textFieldWrapper).setText("Some text");
    }
}
