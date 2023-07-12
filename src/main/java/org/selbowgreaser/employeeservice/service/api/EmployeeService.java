package org.selbowgreaser.employeeservice.service.api;


import org.selbowgreaser.employeeservice.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findEmployeeById(Long id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee updatedEmployee);

    void deleteEmployeeById(Long id);

    boolean isExist(Long id);
}
