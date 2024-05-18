package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClaimDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ClaimDAO claimDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetAllClaims() throws SQLException {
        List<Claim> claims = new ArrayList<>();
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("claimId")).thenReturn(1);
        when(resultSet.getInt("policyHolderId")).thenReturn(1);
        when(resultSet.getInt("dependentId")).thenReturn(1);
        when(resultSet.getDouble("amount")).thenReturn(1000.0);
        when(resultSet.getDate("dateOfClaim").toLocalDate()).thenReturn(LocalDate.of(2023, 5, 18));
        when(resultSet.getString("status")).thenReturn("Pending");

        claims = claimDAO.getAllClaims();

        assertEquals(1, claims.size());
        assertEquals(1, claims.get(0).getClaimId());
    }

    @Test
    public void testAddClaim() throws SQLException {
        Claim claim = new Claim(1, 1, 1, 1000.0, LocalDate.of(2023, 5, 18), "Pending");

        claimDAO.addClaim(claim);

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateClaim() throws SQLException {
        Claim claim = new Claim(1, 1, 1, 1000.0, LocalDate.of(2023, 5, 18), "Pending");

        claimDAO.updateClaim(claim);

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteClaim() throws SQLException {
        int claimId = 1;

        claimDAO.deleteClaim(claimId);

        verify(preparedStatement, times(1)).executeUpdate();
    }
}
