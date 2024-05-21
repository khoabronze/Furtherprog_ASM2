/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;



import java.util.List;
import java.util.Optional;

public interface PolicyOwnerDAO <PO> {
    List<PO> getAll();
    Optional<PO> get(String i);
    PO getOne(String i);
    boolean add(PO po);
    boolean update(PO po);
    boolean delete(PO po);
}


