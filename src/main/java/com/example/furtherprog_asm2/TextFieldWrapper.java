package com.example.furtherprog_asm2;

import javafx.scene.control.TextField;

public class TextFieldWrapper {
    private TextField textField;

    public TextFieldWrapper(TextField textField) {
        this.textField = textField;
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public String getText() {
        return textField.getText();
    }
}
