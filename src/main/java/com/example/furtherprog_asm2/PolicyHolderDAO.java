package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public interface PolicyHolderDAO <PH>{
    List<PH> getAll();
    Optional<PH> get(String i);
    PH getOne(String i);
    boolean add(PH po);
    boolean update(PH po);
    boolean delete(PH po);
}


