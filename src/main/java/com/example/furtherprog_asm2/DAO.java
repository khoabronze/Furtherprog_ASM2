package com.example.furtherprog_asm2;

import java.util.HashMap;
import java.util.Optional;

public interface DAO<IC> {
    HashMap<String, IC> getAll();
    Optional<IC> get(String cardNumber);
    InsuranceCard getOne(String cardNumber);
    boolean add(IC ic);
    void update(IC ic);
    boolean delete(IC ic);
}
