package com.example.furtherprog_asm2;

import java.util.List;

public interface DAO<T> {
    T get(String id);
    List<T> getAll();
    void save(T t);
    boolean update(T t);
    void delete(T t);
}