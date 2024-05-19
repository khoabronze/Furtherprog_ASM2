package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class DependentDAO_IMPTest {
    private Connection connectionMock;
    private DependentDAO_IMP dependentDAOImp;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        dependentDAOImp = new DependentDAO_IMP(connectionMock);
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<Dependent> dependents = dependentDAOImp.getAll();
        assertEquals(3, dependents.size());
    }
    @Test
    void getAllDepedentIsEmpty() throws SQLException{
        List<Dependent> dependents = dependentDAOImp.getAll();
        assertFalse(dependents.isEmpty());
    }
    @Test
    void getAllDepedentThrowsExceoption() throws SQLException{
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dependentDAOImp.getAll();
        });
        assertEquals(thrown.getMessage(),"SQL not connect" );
    }
    @Test
    void getDepedentIdSuccess() {
        Optional<Dependent> optionalDependent = dependentDAOImp.get("12");
        assertTrue(optionalDependent.isPresent());
        Dependent dependent = optionalDependent.get();
        assertEquals("12", dependent.getId());
    }
    @Test
    void getDepedentByIdNotFound() {
        Optional<Dependent> optionalDependent = dependentDAOImp.get("nonexistent");
        assertTrue(optionalDependent.isEmpty());
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
        dependent.setId("2222");//fail : khi id không tồn tại trong db , pass : id có tồn tại trong db
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
        dependent.setPhone("012345678");
        dependent.setEmail("a@gmail.com");
        dependent.setAddress("Hà Nội");
        dependent.setPassword("abc123");
        dependent.setId("12");
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
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dependentDAOImp.delete(dependent);
        });
        assertEquals("Error deleting dependent", thrown.getMessage());

    }

}