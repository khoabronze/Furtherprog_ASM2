package com.example.furtherprog_asm2;

import javafx.scene.control.PasswordField;

public class PasswordFieldWrapper {
    private PasswordField passwordField;

    public PasswordFieldWrapper(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public void setText(String text) {
        passwordField.setText(text);
    }

    public String getText() {
        return passwordField.getText();
    }
}
