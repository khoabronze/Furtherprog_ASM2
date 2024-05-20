package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class RequestDAOTest {
    private Connection connectionMock;
    private RequestDAO requestDAO;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        requestDAO = new RequestDAO();
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<Request> requests = requestDAO.getAll();
        assertEquals(20, requests.size());
    }
    @Test
    void getAllRequestDAOIsEmpty() throws SQLException{
        List<Request> RequestDAOs = requestDAO.getAll();
        assertFalse(RequestDAOs.isEmpty());
    }

    @Test
    void getRequestDAOByIdSuccess() {
        Optional<Request> optionalRequestDAO = requestDAO.get("r-1685332736");
        assertTrue(optionalRequestDAO.isPresent());
        Request request = optionalRequestDAO.get();
        assertEquals("r-1685332736", request.getRid());
    }
    @Test
    void getRequestDAOByCardNumberNotFound() {
        Optional<Request> optionalRequestDAO = requestDAO.get("nonexistent");
        assertTrue(optionalRequestDAO.isEmpty());
    }




    @Test
    void processRequestSuccess() throws SQLException {
        String rid = "r-1685332736";
        String id = "C-6322056522";
        requestDAO.processRequest(rid, id, Approval.DECLINED.toString());
        Request request = requestDAO.getOne(rid);
        assertEquals(Approval.DECLINED, request.getApproval());
    }
    @Test
    void switchStatusRuntimeException() {
        String rid = "r-001111111";
        String id = "1234567866";
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            requestDAO.processRequest(rid, id, Approval.DECLINED.toString());
        });
        assertEquals("No request found with id: " + rid, thrown.getMessage());

    }


}