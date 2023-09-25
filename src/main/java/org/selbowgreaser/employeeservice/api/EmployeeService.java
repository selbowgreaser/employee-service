package org.selbowgreaser.employeeservice.api;


import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    /*
    TODO общие замечание
        - я бы опустил слово Employees, так как в рамках одного сервиса (у которого есть конкретная сущность) понятно тот или иной метод
        - также при текущем наименовании идут разногласия в названиях: findEmployeeById и findAll
        - createBatchEmployees - лучше убрать слово Batch и оставил Employees
     */

    void createBatchEmployees(List<EmployeeRequestDto<?>> employeeRequestDtos, List<Map<String, String>> allErrors); //todo вкусовщина можно опустить слово Dto и смысл останется таким же, так как Request у тебя один на конкртеную сущность

    List<Employee> findAll();

    Employee findEmployeeById(Long id);

    void updateEmployee(Long id, EmployeeRequestDto<?> updatedEmployeeRequestDto, Map<String, String> errors);

    void deleteEmployeeById(Long id);

    boolean isExist(Long id);
}
