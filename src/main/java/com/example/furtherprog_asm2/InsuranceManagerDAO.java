/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public interface InsuranceManagerDAO <InsuranceManager>{
    List<InsuranceManager> getAll();
    Optional<InsuranceManager> get(String id);
    InsuranceManager getOne(String id);
    boolean add(InsuranceManager im);
    boolean update(InsuranceManager im);
    boolean delete(InsuranceManager im);
}
