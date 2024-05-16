package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;


public interface DAO<T> {
    List<T> getAll();
    Optional<T> get(String i);
    T getOne(String i);
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}
