module com.example.furtherprog_asm2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.furtherprog_asm2 to javafx.fxml;
    exports com.example.furtherprog_asm2;
}