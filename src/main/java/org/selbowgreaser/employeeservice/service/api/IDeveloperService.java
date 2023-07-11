package org.selbowgreaser.employeeservice.service.api;

import org.selbowgreaser.employeeservice.model.Developer;

public interface IDeveloperService {

    Developer findDeveloperById(Long id);

    void saveDeveloper(Developer developer);

    void updateDeveloper(Developer updatedDeveloper);

    void deleteDeveloperById(Long id);

    boolean isExist(Long id);
}
