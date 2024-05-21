package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DependentDAO_IMPTest {
    private Connection connectionMock;
    private DependentDAO_IMP dependentDAOImp;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        dependentDAOImp = new DependentDAO_IMP(connectionMock);
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<Dependent> dependents = dependentDAOImp.getAll();
        assertNotNull(dependents, "The list of dependents should not be null");
    }

    @Test
    void getDependentSuccess() {
        String validId = "D-1357593140";

        Optional<Dependent> optionalDependent = dependentDAOImp.get(validId);
        assertTrue(optionalDependent.isPresent(), "The Optional<Dependent> should not be empty");

        Dependent dependent = optionalDependent.get();
        assertEquals(validId, dependent.getId(), "The ID of the retrieved dependent should match the provided ID");
    }
    @Test
    void getDepedentByIdNotFound() {
        Optional<Dependent> optionalDependent = dependentDAOImp.get("D-1357593140");
        assertTrue(optionalDependent.isPresent());
        Dependent dependent = optionalDependent.get();
        assertTrue( optionalDependent.isEmpty());
    }
    @Test
    void addSuccess() throws SQLException {
        Dependent dependent = new Dependent();
        dependent.setPhone("12");
        dependent.setEmail("a@gmail.com");
        dependent.setAddress("Hà Nội");
        dependent.setPassword("abc123");
        boolean result = dependentDAOImp.add(dependent);
        assertTrue(result);
        dependentDAOImp.delete(dependent);
    }
    @Test
    void addFail() throws SQLException {
        Dependent dependent = new Dependent();
        dependent.setPhone(null);
        dependent.setEmail("a@gmail.com");
        dependent.setAddress("Hà Nội");
        dependent.setPassword("abc123");
        boolean result = dependentDAOImp.add(dependent);
        assertFalse(result);
    }
    @Test
    void updateDependentSuccess() throws SQLException {
        Dependent dependent = new Dependent();
        dependent.setPhone("012345678");
        dependent.setEmail("a@gmail.com");
        dependent.setAddress("Hà Nội");
        dependent.setPassword("abc123");
        dependent.setId("2222");
        boolean result = dependentDAOImp.update(dependent);
        assertTrue(result);
    }
    @Test
    void updateDependentThrowException() throws SQLException {
        Dependent dependent = new Dependent();
        dependent.setPhone("012345678");
        dependent.setEmail("a@gmail.com");
        dependent.setAddress("Hà Nội");
        dependent.setPassword("abc123");
        dependent.setId("12ádasd");
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dependentDAOImp.update(dependent);
        });

        assertEquals(thrown.getMessage(), "Error updating dependent");
    }

    @Test
    void deleteDependentSucces() {
        Dependent dependent = new Dependent();
        dependent.setId("d-1234567899");
        dependent.setName("John Cena");
        dependent.setEmail("kdoakdo@gmail.com");
        dependent.setPhone("0123456789");
        dependent.setAddress("ưeqweqw");
        dependent.setPassword("abc123");
        dependentDAOImp.add(dependent);
        boolean result = dependentDAOImp.delete(dependent);
        assertTrue(result);
    }
    @Test
    void deleteClaimNotFound() {
        Dependent dependent = new Dependent();
        dependent.setId("ádasdasdas");

        boolean result = dependentDAOImp.delete(dependent);
        assertFalse(result);
    }
    @Test
    void deleteClaimException() {
        Dependent dependent = null;
        assertThrows(NullPointerException.class, () -> dependentDAOImp.delete(dependent));
    }

}