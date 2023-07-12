package org.selbowgreaser.employeeservice.service.api;

import org.selbowgreaser.employeeservice.model.Engineer;

public interface EngineerService {

    Engineer findEngineerById(Long id);

    void saveEngineer(Engineer engineer);

    void updateEngineer(Engineer updatedEngineer);

    void deleteEngineerById(Long id);

    boolean isExist(Long id);
}
