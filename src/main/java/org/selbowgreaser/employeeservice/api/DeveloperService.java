package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.selbowgreaser.employeeservice.model.request.DeveloperDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;

import java.util.List;
import java.util.Map;

public interface DeveloperService {

    /*
    TODO общие замечание
        я бы опустил слово Developer, так как в рамках одного сервиса (у которого есть конкретная сущность) понятно тот или иной метод
        также при текущем наименовании идут разногласия в названиях: findDeveloperById и findAll
     */

    void createDeveloper(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors);

    List<Developer> findAll();

    Developer findDeveloperById(Long id);

    void updateDeveloper(Long id, DeveloperDto updatedDeveloperDto, Map<String, String> errors);

    void deleteDeveloperById(Long id);

    boolean isExist(Long id);
}
