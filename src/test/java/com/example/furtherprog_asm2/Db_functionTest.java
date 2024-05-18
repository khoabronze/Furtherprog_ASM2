package com.example.furtherprog_asm2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_functionTest {
    private Db_function dbFunction;
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        dbFunction = mock(Db_function.class);
        mockConnection = mock(Connection.class);
    }

    @Test
    public void testGetConnection() throws SQLException {
        when(dbFunction.getConnection()).thenReturn(mockConnection);

        Connection connection = dbFunction.getConnection();
        assertNotNull(connection);
        verify(dbFunction, times(1)).getConnection();
    }
}
