package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.selbowgreaser.employeeservice.model.request.DeveloperDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;

import java.util.List;
import java.util.Map;

public interface DeveloperService {

    void createDeveloper(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors);

    List<Developer> findAll();

    Developer findDeveloperById(Long id);

    void updateDeveloper(Long id, DeveloperDto updatedDeveloperDto, Map<String, String> errors);

    void deleteDeveloperById(Long id);

    boolean isExist(Long id);
}
