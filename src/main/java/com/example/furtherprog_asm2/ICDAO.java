/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public interface ICDAO <InsuranceCard> {
    List<InsuranceCard> getAll();
    Optional<InsuranceCard> get(String cardNumber);
    InsuranceCard getOne(String cardNumber);
    boolean add(InsuranceCard ic);
    boolean update(String cardNumber, String cardHolder, String policyOwner, String expirationDate);
    boolean delete(InsuranceCard ic);
}
