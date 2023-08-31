package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.api.*;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.repository.EmployeeRepository;
import org.selbowgreaser.employeeservice.util.validator.EmployeeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DeveloperService developerService;
    private final DesignerService designerService;
    private final ManagerService managerService;
    private final EngineerService engineerService;
    private final AnalystService analystService;
    private final EmployeeValidator employeeValidator;
    private final ModelMapper modelMapper;

    @Override
    public void createBatchEmployees(List<EmployeeRequestDto<?>> employeeRequestDtos, List<Map<String, String>> allErrors) {
        employeeRequestDtos.forEach(employeeRequest -> {
            log.info("Creating new employee");

            Map<String, String> errors = new HashMap<>();

            switch (employeeRequest.getPosition()) {
                case MANAGER -> managerService.createManager(employeeRequest, errors);
                case ANALYST -> analystService.createAnalyst(employeeRequest, errors);
                case DESIGNER -> designerService.createDesigner(employeeRequest, errors);
                case DEVELOPER -> developerService.createDeveloper(employeeRequest, errors);
                case ENGINEER -> engineerService.createEngineer(employeeRequest, errors);
            }

            allErrors.add(errors);
        });
    }

    @Override
    public List<Employee> findAll() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        log.info("Fetching employee by id: {}", id);
        return employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequestDto<?> updatedEmployeeRequestDto, Map<String, String> errors) {
        log.info("Updating employee with id: {}", id);

        Employee updatedEmployee = modelMapper.map(updatedEmployeeRequestDto, Employee.class);

        updatedEmployee.setId(id);

        employeeValidator.validate(updatedEmployee, errors);

        if (errors.isEmpty()) {
            employeeRepository.save(updatedEmployee);
            log.info("Employee updated successfully");
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        log.info("Deleting employee with id: {}", id);

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        employeeRepository.deleteById(id);

        log.info("Employee deleted successfully");
    }

    @Override
    public boolean isExist(Long id) {
        return employeeRepository.existsById(id);
    }
}
