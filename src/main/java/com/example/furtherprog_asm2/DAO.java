package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public interface DAO<IC> {
    List<IC> getAll();
    Optional<IC> get(String cardNumber);
    InsuranceCard getOne(String cardNumber);
    boolean add(IC ic);
    boolean update(IC ic);
    boolean delete(IC ic);
}
