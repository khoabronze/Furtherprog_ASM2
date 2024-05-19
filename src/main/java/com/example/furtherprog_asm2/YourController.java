package com.example.furtherprog_asm2;

public class YourController {
    private TextFieldWrapper textFieldWrapper;

    public YourController(TextFieldWrapper textFieldWrapper) {
        this.textFieldWrapper = textFieldWrapper;
    }

    public void someMethod() {
        textFieldWrapper.setText("Some text");
    }

    // Other methods
}
