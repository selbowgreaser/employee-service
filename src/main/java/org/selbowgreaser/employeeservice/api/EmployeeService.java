package org.selbowgreaser.employeeservice.api;


import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void createBatchEmployees(List<EmployeeRequestDto<?>> employeeRequestDtos, List<Map<String, String>> allErrors);

    List<Employee> findAll();

    Employee findEmployeeById(Long id);

    void updateEmployee(Long id, EmployeeRequestDto<?> updatedEmployeeRequestDto, Map<String, String> errors);

    void deleteEmployeeById(Long id);

    boolean isExist(Long id);
}
