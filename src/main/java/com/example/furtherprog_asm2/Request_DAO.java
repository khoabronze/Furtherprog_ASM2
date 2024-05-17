package com.example.furtherprog_asm2;

import java.util.Optional;
import java.util.List;

public interface Request_DAO<Request> {
    List<Request> getAll();
    Optional<Request> get(String rid);
    Request getOne(String rid);
    boolean create(Request request);
    boolean update(Request request);
    void processRequest(String rid, String id, String approval);

}
