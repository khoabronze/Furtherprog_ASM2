/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import java.util.List;
import java.util.Optional;

public class DependentService {
    private DependentDAO_IMP dependentDAO;

    public DependentService(DependentDAO_IMP dependentDAO) {
        this.dependentDAO = dependentDAO;
    }

    public List<Dependent> getAllDependents() {
        return dependentDAO.getAll();
    }

    public Optional<Dependent> getDependent(String id) {
        return dependentDAO.get(id);
    }

    public Dependent getOneDependent(String id) {
        return dependentDAO.getOne(id);
    }

    public boolean addDependent(Dependent dependent) {
        return dependentDAO.add(dependent);
    }

    public boolean updateDependent(Dependent dependent) {
        return dependentDAO.update(dependent);
    }

    public boolean deleteDependent(Dependent dependent) {
        return dependentDAO.delete(dependent);
    }
}