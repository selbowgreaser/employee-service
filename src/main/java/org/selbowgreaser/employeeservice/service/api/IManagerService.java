package org.selbowgreaser.employeeservice.service.api;

import org.selbowgreaser.employeeservice.model.Manager;

public interface IManagerService {

    Manager findManagerById(Long id);

    void saveManager(Manager manager);

    void updateManager(Manager updatedManager);

    void deleteManagerById(Long id);

    boolean isExist(Long id);
}
