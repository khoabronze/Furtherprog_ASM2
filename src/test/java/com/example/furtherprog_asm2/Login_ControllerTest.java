package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
class Login_ControllerTest {
    @Mock
    private TextField Username_box;

    @Mock
    private PasswordField Password_box;
    @Mock
    private Alert alert;
    @Mock
    private Button login_button;

    @Mock
    private Db_function db;

    @InjectMocks
    private Login_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin_Successful() throws SQLException {
        String username = "testuser";
        String password = "testpassword";
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(Username_box.getText()).thenReturn(username);
        when(Password_box.getText()).thenReturn(password);
        when(db.connect_to_db()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getString("password")).thenReturn(password);

        controller.login(new ActionEvent());

        verify(rs).close();
        verify(stmt).close();
        verify(conn).close();
        verifyNoMoreInteractions(rs, stmt, conn);
    }

    @Test
    public void testLogin_Failed() throws SQLException {
        String username = "testuser";
        String password = "testpassword";
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(Username_box.getText()).thenReturn(username);
        when(Password_box.getText()).thenReturn(password);
        when(db.connect_to_db()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getString("password")).thenReturn("wrongpassword");

        controller.login(new ActionEvent());
        verify(alert, times(1)).setTitle("Login Failed");
        verify(alert, times(1)).setHeaderText(null);
        verify(alert, times(1)).setContentText("Invalid username or password");
        verify(alert, times(1)).showAndWait();
        verify(rs).close();
        verify(stmt).close();
        verify(conn).close();
        verifyNoMoreInteractions(rs, stmt, conn);

    }



}