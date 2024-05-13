package com.example.furtherprog_asm2;

import java.util.HashMap;
import java.util.Optional;

public interface DAO<IC> {
    HashMap<String, IC> getAll();
    Optional<IC> get(String cardNumber);
    void getOne(IC ic);
    boolean add(IC ic);
    void update(IC ic);
    void delete(IC ic);
}
