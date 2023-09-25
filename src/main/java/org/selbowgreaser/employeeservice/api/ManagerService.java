package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.position.Manager;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.request.ManagerDto;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    /*
    TODO общие замечание
        я бы опустил слово Manager, так как в рамках одного сервиса (у которого есть конкретная сущность) понятно тот или иной метод
        также при текущем наименовании идут разногласия в названиях: findManagerById и findAll
     */

    void createManager(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors);

    List<Manager> findAll();

    Manager findManagerById(Long id);

    void updateManager(Long id, ManagerDto updatedManagerDto, Map<String, String> errors);

    void deleteManagerById(Long id);

    boolean isExist(Long id);
}
