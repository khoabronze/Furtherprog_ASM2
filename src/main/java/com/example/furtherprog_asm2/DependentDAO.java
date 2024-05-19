package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public interface DependentDAO <D> {
        List<D> getAll();
        Optional<D> get(String i);
        D getOne(String i);
        boolean add(D d);
        boolean update(D d);
        boolean delete(D d);
    }


