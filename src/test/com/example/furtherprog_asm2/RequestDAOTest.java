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
        assertEquals(22, requests.size());
    }
    @Test
    void getAllRequestDAOIsEmpty() throws SQLException{
        List<Request> RequestDAOs = requestDAO.getAll();
        assertFalse(RequestDAOs.isEmpty());
    }
    @Test
    void getAllRequestDAOThrowsExceoption() throws SQLException{
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            requestDAO.getAll();
        });
        assertEquals(thrown.getMessage(),"SQL not connect" );
    }
    @Test
    void getRequestDAOByIdSuccess() {
        Optional<Request> optionalRequestDAO = requestDAO.get("1234567898");
        assertTrue(optionalRequestDAO.isPresent());
        Request request = optionalRequestDAO.get();
        assertEquals("1234567898", request.getId());
    }
    @Test
    void getRequestDAOByCardNumberNotFound() {
        Optional<Request> optionalRequestDAO = requestDAO.get("nonexistent");
        assertTrue(optionalRequestDAO.isEmpty());
    }
    @Test
    void addSuccess() throws SQLException {
        Request request = new Request();
        request.setRid("r-111");
        request.setId("098761234");
        request.setNote("Note");
        request.setApproval(Approval.APPROVED);
        boolean result = requestDAO.create(request);
        assertTrue(result);
    }
    @Test
    void addFail() throws SQLException {
        Request request = new Request();
        request.setRid("r-111");
        request.setId("098761234");
        request.setNote("Note");
        request.setApproval(Approval.APPROVED);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            requestDAO.create(request);
        });
        assertEquals("Create fail", thrown.getMessage());
    }

    @Test
    void updateSuccess() throws SQLException {
        Request request = new Request();
        request.setRid("r-111");
        request.setId("1234567898");
        request.setNote("Note");
        request.setApproval(Approval.APPROVED);
        boolean result = requestDAO.update(request);
        assertTrue(result);

    }
    @Test
    void updateFail() throws SQLException {
        Request request = new Request();
        request.setRid("r-111");
        request.setId(null);
        request.setNote("Note");
        request.setApproval(Approval.APPROVED);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            requestDAO.update(request);
        });
        assertEquals("Update fail", thrown.getMessage());

    }
    @Test
    void processRequestSuccess() throws SQLException {
        String rid = "r-001";
        String id = "1234567866";
        requestDAO.processRequest(rid, id, Approval.DECLINED.toString());
        Request request = requestDAO.getOne(rid);
        assertEquals("DECLINED", request.getApproval());

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